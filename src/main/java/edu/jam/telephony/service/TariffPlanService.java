package edu.jam.telephony.service;

import edu.jam.telephony.model.entity.TariffPlan;

import java.util.List;

public interface TariffPlanService {

    void add(TariffPlan object);

    void addAll(List<TariffPlan> objects);

    TariffPlan get(int id);

    List<TariffPlan> getAll();

    int getCount();

}
