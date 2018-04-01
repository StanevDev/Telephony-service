package edu.jam.telephony.controller;

import edu.jam.telephony.dao.TariffPlanDao;
import edu.jam.telephony.model.entity.TariffPlan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class TariffPlanController {

    final TariffPlanDao repository;

    @Autowired
    public TariffPlanController(TariffPlanDao repository) {
        this.repository = repository;
    }

    @RequestMapping("/get")
    public TariffPlan getTariff(){
        return null;
    }

}
