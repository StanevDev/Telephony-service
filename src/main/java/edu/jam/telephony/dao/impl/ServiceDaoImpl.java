package edu.jam.telephony.dao.impl;

import edu.jam.telephony.dao.ServiceDao;
import edu.jam.telephony.model.entity.Service;
import edu.jam.telephony.model.entity.ServiceType;
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
public class ServiceDaoImpl extends JdbcDaoSupport implements ServiceDao {

    private final DataSource dataSource;

    @Autowired
    public ServiceDaoImpl(DataSource db) {
        this.dataSource = db;
    }

    @PostConstruct
    private void initialize() {
        setDataSource(dataSource);
    }

    @Override
    public void add(Service service) {
        final String sql = "INSERT INTO service " +
                "(price, tarification_value, service_type, service_name) " +
                "VALUES (?,?,?,?)";

        getJdbcTemplate().update(
                sql,
                service.getPrice(),
                service.getTarificationValue(),
                service.getServiceType(),
                service.getServiceName()
        );
    }

    @Override
    public void addAll(List<Service> services) {
        final String sql = "INSERT INTO service " +
                "(price, tarification_value, service_type, service_name) " +
                "VALUES (?,?,?,?)";

        final var batchUpdate = new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                var service = services.get(i);
                ps.setBigDecimal(1, service.getPrice());
                ps.setInt(2, service.getTarificationValue());
                ps.setString(3, service.getServiceType().name());
                ps.setString(4, service.getServiceName());
            }

            @Override
            public int getBatchSize() {
                return services.size();
            }
        };

        getJdbcTemplate().batchUpdate(sql, batchUpdate);
    }

    @Override
    public Service get(int id) {
        String sql = "SELECT * FROM service WHERE service_id = ?";
        return getJdbcTemplate().queryForObject(sql, serviceRowMapper);
    }

    @Override
    public List<Service> getBySubscriber(int userId){
        final String sql = "SELECT * FROM service s WHERE s.service_id in ( " +
                "SELECT sms.service_id from service_mm_subscriber sms WHERE sms.subscriber_id = " + userId+ ")";

        return getJdbcTemplate().query(
                sql,
                serviceExtractor);
    }

    @Override
    public List<Service> getAll() {
        final String sql = "SELECT * FROM service";
        return getJdbcTemplate().query(sql, serviceExtractor);
    }

    @Override
    public int getCount() {
        final String sql = "SELECT COUNT(*) FROM service";
        return getJdbcTemplate().queryForObject(sql, Integer.class);
    }

    private RowMapper<Service> serviceRowMapper = (rs, rowNum) ->
            new Service(
                    rs.getInt("service_id"),
                    rs.getBigDecimal("price"),
                    rs.getInt("tarification_value"),
                    ServiceType.parse(rs.getString("service_type")),
                    rs.getString("service_name")
            );

    private ResultSetExtractor<List<Service>> serviceExtractor = rs -> {
        List<Service> list = new ArrayList<>();

        while (rs.next()) {
            list.add(serviceRowMapper.mapRow(rs, rs.getRow()));
        }
        return list;
    };
}
