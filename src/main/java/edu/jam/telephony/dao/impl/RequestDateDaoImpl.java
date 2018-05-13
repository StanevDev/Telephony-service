package edu.jam.telephony.dao.impl;

import edu.jam.telephony.dao.RequestDateDao;
import edu.jam.telephony.model.entity.RequestDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class RequestDateDaoImpl extends JdbcDaoSupport implements RequestDateDao {

    final DataSource dataSource;

    @Autowired
    public RequestDateDaoImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @PostConstruct
    public void initialize (){
        setDataSource(dataSource);
    }

    @Override
    public void add(RequestDate date) {
        String sql = "INSERT INTO tech_request " +
                "(request_date) " +
                "VALUES(?)";

        getJdbcTemplate()
                .update(sql,
                        new Date(date.getRequestDate().getTime())
                );
    }

    @Override
    public void addAll(List<RequestDate> dates) {
        final String sql = "INSERT INTO tech_request " +
                "(request_date) " +
                "VALUES(?)";

        final var setter = new BatchPreparedStatementSetter(){

            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                var date = dates.get(i);

                ps.setDate(1, new Date(date.getRequestDate().getTime()));
            }

            @Override
            public int getBatchSize() {
                return dates.size();
            }
        };

        getJdbcTemplate().batchUpdate(sql, setter);
    }

    @Override
    public RequestDate get(int id) {
        final String sql = "SELECT * FROM tech_request_date WHERE date_id = ?";

        return getJdbcTemplate().queryForObject(
                sql,
                new Object[]{id},
                requestDateRowMapper
        );
    }

    @Override
    public List<RequestDate> getAll() {
        final String sql = "SELECT * FROM tech_request_date";
        return getJdbcTemplate().query(sql, requestDateExtractor);
    }

    @Override
    public int getCount() {
        final String sql = "SELECT COUNT (*) FROM tech_request_date";
        return getJdbcTemplate().queryForObject(sql, Integer.class);
    }

    private RowMapper<RequestDate> requestDateRowMapper = (rs, rowNum) ->
        new RequestDate(
                rs.getInt("date_id"),
                new java.util.Date(rs.getDate("request_date").getTime())
        );

    private ResultSetExtractor<List<RequestDate>> requestDateExtractor = rs -> {
        var list = new ArrayList<RequestDate>();

        while (rs.next()){
            list.add(requestDateRowMapper.mapRow(rs, rs.getRow()));
        }
        return list;
    };
}
