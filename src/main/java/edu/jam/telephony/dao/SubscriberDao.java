package edu.jam.telephony.dao;

import edu.jam.telephony.model.entity.Subscriber;

import java.util.List;

public interface SubscriberDao {
    void add(Subscriber sub);

    void addAll(List<Subscriber> subscribers);

    Subscriber get(int id);

    List<Subscriber> getAll();

    void updateTariffPlan(Subscriber subscriber, int planId);

    int getCount();

    Subscriber getByEmail(String email);
}
