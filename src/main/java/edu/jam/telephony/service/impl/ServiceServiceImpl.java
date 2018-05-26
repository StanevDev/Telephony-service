package edu.jam.telephony.service.impl;

import edu.jam.telephony.dao.ServiceDao;
import edu.jam.telephony.model.entity.Service;
import edu.jam.telephony.model.entity.Subscriber;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@org.springframework.stereotype.Service
public class ServiceServiceImpl implements ServiceService {

    @Autowired
    ServiceDao dao;

    @Override
    public List<Service> getAllServices() {
        return dao.getAll();
    }

    @Override
    public List<Service> getServicesBySubscriber(Subscriber sub) {
        return dao.getBySubscriber(sub.getSubscriberId());
    }

    @Override
    public void addServiceToSubscriber(int serviceId, Subscriber sub) {
        dao.addToSubscriber(serviceId, sub.getSubscriberId());
    }

    @Override
    public void removeServiceFromSubscriber(int serviceId, Subscriber sub) {
        dao.removeServiceFromSubscriber(serviceId, sub.getSubscriberId());
    }

    @Override
    public edu.jam.telephony.model.entity.Service get(int id) {
        return dao.get(id);
    }

}
