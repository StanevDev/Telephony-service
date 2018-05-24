package edu.jam.telephony.service.impl;

import edu.jam.telephony.model.entity.Service;
import edu.jam.telephony.model.entity.Subscriber;

import java.util.List;

public interface ServiceService {

    List<Service> getAllServices();

    List<Service> getServicesBySubscriber(Subscriber sub);

}
