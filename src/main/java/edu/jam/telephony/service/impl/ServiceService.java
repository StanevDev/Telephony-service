package edu.jam.telephony.service.impl;

import edu.jam.telephony.model.entity.Service;
import edu.jam.telephony.model.entity.Subscriber;

import java.util.List;

public interface ServiceService {

    List<Service> getAllServices();

    List<Service> getServicesBySubscriber(Subscriber sub);

    void addServiceToSubscriber(int serviceId, Subscriber sub);

    void removeServiceFromSubscriber(int serviceId, Subscriber sub);

    Service get(int id);

}
