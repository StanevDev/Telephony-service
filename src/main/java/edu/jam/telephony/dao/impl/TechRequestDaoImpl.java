package edu.jam.telephony.dao.impl;

import edu.jam.telephony.model.entity.TechRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public class TechRequestDaoImpl extends JdbcDaoSupport implements edu.jam.telephony.dao.TechRequestDao {

    private final DataSource dataSource;

    @Autowired
    public TechRequestDaoImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @PostConstruct
    void initialize() {
        setDataSource(dataSource);
    }

    @Override
    public void add(TechRequest request) {
        final String sqlRequestDate = "INSERT INTO tech_request_date (request_date) VALUES (?)";

        final String sql = "INSERT INTO tech_request " +
                "(problem_description, subscriber_id, tech_support_user_id, date) " +
                "VALUES (?,?,?,?)";

        getJdbcTemplate().update(sql,
                request.getProblemDescription(),
                request.getSubscriberId(),
                request.getTechSupportUserId(),
                new java.sql.Date(request.getDate().getTime()));
    }

    @Override
    public void addAll(List<TechRequest> requests) {
        final String sql = "INSERT INTO tech_request " +
                "(problem_description, subscriber_id, tech_support_user_id) " +
                "VALUES (?,?,?,?)";

        final var batchUpdate = new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                var request = requests.get(i);

                ps.setString(1, request.getProblemDescription());
                ps.setInt(3, request.getSubscriberId());
                ps.setInt(4, request.getTechSupportUserId());
            }

            @Override
            public int getBatchSize() {
                return requests.size();
            }
        };

        getJdbcTemplate().batchUpdate(sql, batchUpdate);
    }

    @Override
    public TechRequest get(int id) {
        final String sql = "SELECT * FROM tech_request WHERE tech_reqeust_id = ?";

        return getJdbcTemplate().queryForObject(
                sql,
                new Object[]{id},
                requestMapper);
    }

    @Override
    public List<TechRequest> getAll() {
        final String sql = "SELECT * FROM tech_request";
        return getJdbcTemplate().query(sql, requestExtractor);
    }

    @Override
    public int getCount() {
        final String sql = "SELECT COUNT(*) FROM tech_request";
        return getJdbcTemplate().queryForObject(sql, Integer.class);
    }

    @Override
    public List<TechRequest> getBySupportUserId(int id){
        final String sql = "SELECT * FROM tech_request WHERE tech_support_user_id = ?";

        return getJdbcTemplate()
                .query(sql,
                        new Object[]{id},
                        requestMapper);
    }

    @Override
    public List<TechRequest> getBySubscriberId(int id) {
        final String sql = "SELECT * FROM tech_request WHERE subscriber_id = ?";

        return getJdbcTemplate().query(
                sql,
                requestExtractor,
                id);
    }

    @Override
    public int getRequestsCountBySupportUserId(int userId){
        final  String sql = "SELECT COUNT(*) FROM tech_request WHERE tech_support_user_id = ?";

        return getJdbcTemplate()
                .queryForObject(
                        sql,
                        new Object[]{userId},
                        Integer.class
                );
    }

    private RowMapper<TechRequest> requestMapper = (rs, rowNum) -> new TechRequest(
            rs.getInt("tech_reqeust_id"),
            rs.getString("problem_description"),
            rs.getInt("subscriber_id"),
            rs.getInt("tech_support_user_id"),
            new Date(rs.getDate("date").getTime())
    );

    private ResultSetExtractor<List<TechRequest>> requestExtractor = rs -> {
        var list = new ArrayList<TechRequest>();

        while (rs.next()){
            list.add(requestMapper.mapRow(rs, rs.getRow()));
        }
        return list;
    };
}
