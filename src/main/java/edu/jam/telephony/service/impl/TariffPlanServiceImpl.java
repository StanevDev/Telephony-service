package edu.jam.telephony.service.impl;

import edu.jam.telephony.dao.TariffPlanDao;
import edu.jam.telephony.dao.impl.TariffPlanDaoImpl;
import edu.jam.telephony.model.entity.TariffPlan;
import edu.jam.telephony.service.TariffPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class TariffPlanServiceImpl implements TariffPlanService {

    final TariffPlanDaoImpl dao;

    @Autowired
    public TariffPlanServiceImpl(TariffPlanDaoImpl dao) {
        this.dao = dao;
    }

    @Override
    public void add(TariffPlan object) {

    }

    @Override
    public void addAll(List<TariffPlan> objects) {

    }

    @Override
    public TariffPlan get(int id) {
        return dao.get(id);
    }

    @Override
    public List<TariffPlan> getAll() {
        return dao.getAll();
    }

    @Override
    public int getCount() {
        return 0;
    }
}
