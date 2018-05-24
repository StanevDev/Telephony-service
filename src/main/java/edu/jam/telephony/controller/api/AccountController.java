package edu.jam.telephony.controller.api;

import edu.jam.telephony.dao.SubscriberDao;
import edu.jam.telephony.model.entity.Subscriber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/api/account")
public class AccountController {

    @Autowired
    SubscriberDao subDao;

    @GetMapping("/get")
    @ResponseBody
    Subscriber some(Principal principal){
        var subscriber = subDao.getByEmail(principal.getName());
        return subscriber;
    }

}
