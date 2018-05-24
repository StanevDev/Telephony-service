package edu.jam.telephony.controller.api;

import edu.jam.telephony.dao.SubscriberDao;
import edu.jam.telephony.model.entity.Subscriber;
import edu.jam.telephony.model.entity.TariffPlan;
import edu.jam.telephony.service.TariffPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping ("/api/tariff")
public class TariffPlanController {

    @Autowired TariffPlanService service;
    @Autowired SubscriberDao subDao;

    @GetMapping("get/{id}")
    @ResponseBody
    TariffPlan getById(@PathVariable Integer id){
        return service.get(id);
    };

    @GetMapping("get/all")
    @ResponseBody
    List<TariffPlan> getAll(){
        return service.getAll();
    }

    @PostMapping("change/{id}")
    @ResponseBody
    Subscriber changeTariff(Principal principal, @PathVariable Integer id){
        var subscriber = subDao.getByEmail(principal.getName());
        if (subscriber.getTariffPlanId() == id) return null;

        subDao.updateTariffPlan(subscriber, id);
        subscriber.setTariffPlanId(id);

        return subscriber; //200
    }

}
