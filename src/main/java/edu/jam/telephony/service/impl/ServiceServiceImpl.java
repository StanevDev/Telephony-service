package edu.jam.telephony.service.impl;

import edu.jam.telephony.dao.ServiceDao;
import edu.jam.telephony.model.entity.Subscriber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceServiceImpl implements ServiceService {

    @Autowired
    ServiceDao dao;

    @Override
    public List<edu.jam.telephony.model.entity.Service> getAllServices() {
        return dao.getAll();
    }

    @Override
    public List<edu.jam.telephony.model.entity.Service> getServicesBySubscriber(Subscriber sub) {
        return dao.getBySubscriber(sub.getSubscriberId());
    }

}
