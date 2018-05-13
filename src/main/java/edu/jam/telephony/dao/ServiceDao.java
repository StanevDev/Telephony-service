package edu.jam.telephony.dao;

import edu.jam.telephony.model.entity.Service;

import java.util.List;

public interface ServiceDao {
    void add(Service service);

    void addAll(List<Service> services);

    Service get(int id);

    List<Service> getBySubscriber(int userId);

    List<Service> getAll();

    int getCount();
}
