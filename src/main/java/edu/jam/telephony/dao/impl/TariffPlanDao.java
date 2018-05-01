package edu.jam.telephony.dao.impl;

import edu.jam.telephony.dao.IRepository;
import edu.jam.telephony.model.entity.TariffPlan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
public class TariffPlanDao extends JdbcDaoSupport implements IRepository<TariffPlan> {

    final private DataSource dataSource;

    @Autowired
    public TariffPlanDao(@Qualifier("dataSource") DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @PostConstruct
    private void initialize(){
        setDataSource(dataSource);
    }

    @Override
    public void add(TariffPlan tariffPlan) {
        final String sql = "INSERT INTO tariff_plan " +
                "(price, expires_date, region, tariff_name, tariff_description) " +
                "VALUES (?,?,?,?,?)";

        getJdbcTemplate().update(
                sql,
                tariffPlan.getPrice(),
                tariffPlan.getExpiresDate(),
                tariffPlan.getRegion(),
                tariffPlan.getName(),
                tariffPlan.getDescription());
    }

    @Override
    public void addAll(List<TariffPlan> tariffPlans) {
        final String sql = "INSERT INTO tariff_plan " +
                "(price, expires_date, region, tariff_name, tariff_description) " +
                "VALUES (?,?,?,?,?)";

        final BatchPreparedStatementSetter setter = new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                TariffPlan tp = tariffPlans.get(0);

                ps.setBigDecimal(1, tp.getPrice());
                ps.setDate(2, new Date
                        (tp.getExpiresDate().getTime()));
                ps.setString(3, tp.getRegion());
                ps.setString(4, tp.getName());
                ps.setString(5, tp.getDescription());
            }

            @Override
            public int getBatchSize() {
                return tariffPlans.size();
            }
        };

        getJdbcTemplate().update(sql, setter);
    }

    @Override
    public TariffPlan get(int id) {
        String sql = "SELECT * FROM tariff_plan WHERE tariff_plan_id = ?";

        return getJdbcTemplate().queryForObject(
                sql,
                new Object[]{id},
                tariffPlanRowMapper);
    }

    @Override
    public List<TariffPlan> getAll() {
        final String sql = "SELECT * FROM tariff_plan";
        return getJdbcTemplate().query(sql, tariffExtractor);
    }

    @Override
    public int getCount() {
        final String sql = "SELECT COUNT(*) FROM tariff_plan";
        return getJdbcTemplate().queryForObject(sql, Integer.class);
    }

    private RowMapper<TariffPlan> tariffPlanRowMapper = (rs, rowNum) ->
            new TariffPlan(
                    rs.getInt("tariff_plan_id"),
                    rs.getBigDecimal("price"),
                    rs.getDate("expires_date"),
                    rs.getString("region"),
                    rs.getString("tariff_name"),
                    rs.getString("tariff_description"));

    private ResultSetExtractor<List<TariffPlan>> tariffExtractor = rs -> {
        List<TariffPlan> list = new ArrayList<>();

        while (rs.next()){
            list.add(tariffPlanRowMapper.mapRow(rs, rs.getRow()));
        }
        return list;
    };

}
