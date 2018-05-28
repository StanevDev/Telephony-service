package edu.jam.telephony.dao;

import edu.jam.telephony.model.entity.TechSupportUser;

import java.util.List;

public interface TechSupportUserDao {
    void add(TechSupportUser user);

    void addAll(List<TechSupportUser> users);

    TechSupportUser get(int id);

    List<TechSupportUser> getAll();

    int getCount();

    int getUserIdWithMinRequests();
}
