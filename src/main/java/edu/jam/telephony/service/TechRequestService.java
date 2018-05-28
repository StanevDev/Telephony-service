package edu.jam.telephony.service;

import edu.jam.telephony.model.entity.Subscriber;
import edu.jam.telephony.model.entity.TechRequest;

import java.util.Date;
import java.util.List;

public interface TechRequestService {

    List<TechRequest> getAllRequests();

    int getRequestCount();

    void addTechRequest(TechRequest techRequest);

    TechRequest addTechRequest(String description, Subscriber subscriber);

    List<TechRequest> getSubscriberTechRequests(Subscriber sub);

}
