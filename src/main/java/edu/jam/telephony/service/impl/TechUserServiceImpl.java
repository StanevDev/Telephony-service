package edu.jam.telephony.service.impl;

import edu.jam.telephony.dao.impl.TechSupportUserDao;
import edu.jam.telephony.model.entity.TechSupportUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TechUserServiceImpl {

    final TechSupportUserDao dao;

    @Autowired
    public TechUserServiceImpl(TechSupportUserDao dao) {
        this.dao = dao;
    }

    public List<TechSupportUser> getAllUsers(){
        return dao.getAll();
    }
}
