package edu.jam.telephony.dao.impl;

import edu.jam.telephony.model.entity.TechSupportUser;
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
import java.util.List;

@Repository
public class TechSupportUserDaoImpl extends JdbcDaoSupport implements edu.jam.telephony.dao.TechSupportUserDao {

    final DataSource dataSource;

    @Autowired
    public TechSupportUserDaoImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @PostConstruct
    void initialize() {
        setDataSource(dataSource);
    }

    @Override
    public void add(TechSupportUser user) {
        final String sql = "INSERT INTO tech_support_user " +
                "(first_name, last_name, phone_number) " +
                "VALUES (?,?,?)";

        getJdbcTemplate()
                .update(
                        sql,
                        user.getFirstName(),
                        user.getLastName(),
                        user.getPhoneNumber()
                );
    }

    @Override
    public void addAll(List<TechSupportUser> users) {
        final String sql = "INSERT INTO tech_support_user " +
                "(first_name, last_name, phone_number) " +
                "VALUES (?,?,?)";

        var setter = new BatchPreparedStatementSetter(){
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                var user = users.get(i);

                ps.setString(1, user.getFirstName());
                ps.setString(2, user.getLastName());
                ps.setLong(3, user.getPhoneNumber());
            }

            @Override
            public int getBatchSize() {
                return users.size();
            }
        };

        getJdbcTemplate().batchUpdate(sql, setter);
    }

    @Override
    public TechSupportUser get(int id) {
        String sql = "SELECT * FROM tech_support_user WHERE tech_support_user_id = ?";

        return getJdbcTemplate().queryForObject(
                sql,
                new Object[]{id},
                supportUserRowMapper);
    }

    @Override
    public List<TechSupportUser> getAll() {
        final String sql = "SELECT * FROM tech_support_user";
        return getJdbcTemplate().query(sql, userExtractor);
    }

    @Override
    public int getCount() {
        final String sql = "SELECT COUNT(*) FROM tech_support_user";
        return getJdbcTemplate().queryForObject(sql, Integer.class);
    }

    @Override
    public int getUserIdWithMinRequests() {
        String sql = "SELECT tech_support_user_id from supportUserWithMinRequests";

        return getJdbcTemplate()
                .queryForObject(sql, Integer.class);
    }

    private RowMapper<TechSupportUser> supportUserRowMapper = (rs, rowNum) -> new TechSupportUser(
            rs.getInt("tech_support_user_id"),
            rs.getString("first_name"),
            rs.getString("last_name"),
            rs.getLong("phone_number")
    );

    private ResultSetExtractor<List<TechSupportUser>> userExtractor = rs -> {
        var list = new ArrayList<TechSupportUser>();

        while (rs.next())
            list.add(supportUserRowMapper.mapRow(rs, rs.getRow()));

        return list;
    };
}
