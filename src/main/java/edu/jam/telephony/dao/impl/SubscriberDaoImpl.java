package edu.jam.telephony.dao.impl;

import edu.jam.telephony.dao.SubscriberDao;
import edu.jam.telephony.model.entity.Subscriber;
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
public class SubscriberDaoImpl extends JdbcDaoSupport implements SubscriberDao {

    private final DataSource dataSource;

    @Autowired
    public SubscriberDaoImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @PostConstruct
    private void initialize() {
        setDataSource(dataSource);
    }

    @Override
    public void add(Subscriber sub) {
        final String sql = "INSERT INTO subscriber " +
                "(balance, registration_date, tariff_plan_id, phone_number, ready_to_block, is_in_roaming, street) " +
                "VALUES (?,?,?,?,?,?,?)";

        getJdbcTemplate().update(
                sql,
                sub.getBalance(),
                new Date(sub.getRegistrationDate().getTime()),
                sub.getTariffPlanId(),
                sub.getPhoneNumber(),
                sub.getReadyToBlock(),
                sub.getIsInRoaming(),
                sub.getStreet()
        );
    }

    @Override
    public void addAll(List<Subscriber> subscribers) {
        final String sql = "INSERT INTO subscriber " +
                "(balance, registration_date, tariff_plan_id, phone_number, ready_to_block, is_in_roaming, street) " +
                "VALUES (?,?,?,?,?,?,?)";

        final var batchUpdate = new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                var sub = get(i);

                ps.setBigDecimal(1, sub.getBalance());
                ps.setDate(2, new Date(sub.getRegistrationDate().getTime()));
                ps.setInt(3, sub.getTariffPlanId());
                ps.setLong(4, sub.getPhoneNumber());
                ps.setBoolean(5, sub.getReadyToBlock());
                ps.setBoolean(6, sub.getIsInRoaming());
                ps.setString(7, sub.getStreet());
            }

            @Override
            public int getBatchSize() {
                return subscribers.size();
            }
        };

        getJdbcTemplate()
                .batchUpdate(sql, batchUpdate);
    }

    @Override
    public Subscriber get(int id) {
        final String sql = "SELECT * FROM service WHERE subscriber_id = ?";

        return getJdbcTemplate()
                .queryForObject(sql,
                        new Object[]{id},
                        subscriberRowMapper
                );
    }

    @Override
    public List<Subscriber> getAll() {
        final String sql = "SELECT * FROM subscriber";
        return getJdbcTemplate().query(sql, subscriberExtractor);
    }

    @Override
    public int getCount() {
        final String sql = "SELECT COUNT (*) FROM subscriber";
        return getJdbcTemplate().queryForObject(sql, Integer.class);
    }

    private RowMapper<Subscriber> subscriberRowMapper = (rs, rowNum) -> new Subscriber(
            rs.getInt("subscriber_id"),
            rs.getBigDecimal("balance"),
            rs.getDate("registration_date"),
            rs.getInt("tariff_plan_id"),
            rs.getLong("phone_number"),
            rs.getBoolean("ready_to_block"),
            rs.getBoolean("is_in_roaming"),
            rs.getString("street")
    );

    private ResultSetExtractor<List<Subscriber>> subscriberExtractor = rs -> {
        var subscribers = new ArrayList<Subscriber>();

        while (rs.next()){
            subscribers.add(subscriberRowMapper.mapRow(rs, rs.getRow()));
        }
        return subscribers;
    };
}
