package edu.jam.telephony.service.impl;

import edu.jam.telephony.dao.TechRequestDao;
import edu.jam.telephony.dao.TechSupportUserDao;
import edu.jam.telephony.model.entity.Subscriber;
import edu.jam.telephony.model.entity.TechRequest;
import edu.jam.telephony.model.entity.TechSupportUser;
import edu.jam.telephony.service.TechRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TechRequestServiceImpl implements TechRequestService {

    @Autowired
    TechRequestDao dao;

    @Autowired
    TechSupportUserDao techUserDao;

    @Override
    public List<TechRequest> getAllRequests() {
        return dao.getAll();
    }

    @Override
    public int getRequestCount() {
        return dao.getCount();
    }

    @Override
    public void addTechRequest(TechRequest techRequest) {
        dao.add(techRequest);
    }

    @Override
    public TechRequest addTechRequest(String description, Subscriber subscriber) {
        TechRequest request = new TechRequest();

        int techUserId = techUserDao.getUserIdWithMinRequests();

        request.setProblemDescription(description);
        request.setSubscriberId(subscriber.getSubscriberId());
        request.setDate(new Date());
        request.setTechSupportUserId(techUserId);
        dao.add(request);

        return request;
    }

//    @Override
//    public void addTechRequest(TechRequest partial, Subscriber subscriber) {
//        partial.setSubscriberId(subscriber.getSubscriberId());
//        partial.setTechSupportUserId(1);
//        dao.add(partial);
//    }

    @Override
    public List<TechRequest> getSubscriberTechRequests(Subscriber sub) {
        return dao.getBySubscriberId(
                sub.getSubscriberId()
        );
    }

}
