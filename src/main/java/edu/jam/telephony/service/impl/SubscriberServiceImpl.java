package edu.jam.telephony.service.impl;

import edu.jam.telephony.dao.SubscriberDao;
import edu.jam.telephony.model.entity.Subscriber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
public class SubscriberServiceImpl {

    final SubscriberDao dao;

    @Autowired
    public SubscriberServiceImpl(SubscriberDao dao) {
        this.dao = dao;
    }

    public Subscriber fromPrincipal(Principal principal){
        String email = principal.getName();

        return dao.getByEmail(email);
    }
}
