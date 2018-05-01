package edu.jam.telephony.dao.impl;

import edu.jam.telephony.dao.IRepository;
import edu.jam.telephony.model.entity.Service;
import edu.jam.telephony.model.entity.ServiceType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ServiceDao extends JdbcDaoSupport implements IRepository<Service> {

    private final DataSource dataSource;

    @Autowired
    public ServiceDao(DataSource db) {
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
    public void addAll(List<Service> objects) {
        throw new NotImplementedException();
    }

    @Override
    public Service get(int id) {
        String sql = "SELECT * FROM service WHERE service_id = ?";
        return getJdbcTemplate().queryForObject(sql, serviceRowMapper);
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
                    (ServiceType) rs.getObject("service_type"),
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
