package edu.jam.telephony.dao;

import edu.jam.telephony.model.entity.TariffPlan;

import java.util.List;


public interface TariffPlanDao {

    void add(TariffPlan object);

    void addAll(List<TariffPlan> objects);

    TariffPlan get(int id);

    List<TariffPlan> getAll();

    int getCount();

}
