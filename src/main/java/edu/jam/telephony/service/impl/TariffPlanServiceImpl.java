package edu.jam.telephony.service.impl;

import edu.jam.telephony.dao.TariffPlanDao;
import edu.jam.telephony.model.TariffPlan;
import edu.jam.telephony.service.TariffPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class TariffPlanServiceImpl implements TariffPlanService {

    @Autowired
    TariffPlanDao dao;

    @Override
    public void add(TariffPlan object) {

    }

    @Override
    public void addAll(List<TariffPlan> objects) {

    }

    @Override
    public TariffPlan get(int id) {
        return null;
    }

    @Override
    public List<TariffPlan> getAll() {
        return null;
    }

    @Override
    public int getCount() {
        return 0;
    }
}
