package edu.jam.telephony.dao;

import java.util.List;

public interface RequestDateDao {
    void add(edu.jam.telephony.model.entity.RequestDate date);

    void addAll(List<edu.jam.telephony.model.entity.RequestDate> dates);

    edu.jam.telephony.model.entity.RequestDate get(int id);

    List<edu.jam.telephony.model.entity.RequestDate> getAll();

    int getCount();
}
