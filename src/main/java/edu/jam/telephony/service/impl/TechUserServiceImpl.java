package edu.jam.telephony.service.impl;

import edu.jam.telephony.dao.impl.TechRequestDao;
import edu.jam.telephony.dao.impl.TechSupportUserDao;
import edu.jam.telephony.model.entity.TechSupportUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TechUserServiceImpl {

    private final TechSupportUserDao techUserDao;
    private final TechRequestDao requestDao;

    @Autowired
    public TechUserServiceImpl(TechSupportUserDao dao, TechRequestDao requestDao) {
        this.techUserDao = dao;
        this.requestDao = requestDao;
    }

    public List<TechSupportUser> getAllUsers(){
        return techUserDao.getAll();
    }

    public List<Integer> getRequestsCountForUsers(List<TechSupportUser> users){
        var counts = new ArrayList<Integer>();

        for (TechSupportUser user : users){
            int userId = user.getTechSupportUserId();
            int requestsCount =
                    requestDao.getRequestsCountBySupportUserId(userId);
            counts.add(requestsCount);
        }

        return counts;
    }

    public void addTechUser(TechSupportUser user){
        techUserDao.add(user);
    }
}
