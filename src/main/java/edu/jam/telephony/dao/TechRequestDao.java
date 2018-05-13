package edu.jam.telephony.dao;

import edu.jam.telephony.model.entity.TechRequest;

import java.util.List;

public interface TechRequestDao {
    void add(TechRequest request);

    void addAll(List<TechRequest> requests);

    TechRequest get(int id);

    List<TechRequest> getAll();

    int getCount();

    List<TechRequest> getBySupportUserId(int id);

    int getRequestsCountBySupportUserId(int userId);
}
