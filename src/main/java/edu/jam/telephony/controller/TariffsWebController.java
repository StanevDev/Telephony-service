package edu.jam.telephony.controller;

import edu.jam.telephony.dao.TariffPlanDao;
import edu.jam.telephony.model.entity.TariffPlan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/tariffs")
public class TariffsWebController {

    final TariffPlanDao dao;

    @Autowired
    public TariffsWebController(TariffPlanDao dao) {
        this.dao = dao;
    }

    @GetMapping("/list")
    public String list(Model model){
        List<TariffPlan> tariffs = dao.getAll();
        model.addAttribute("tariffs", tariffs);
        return "tariffs_page";
    }
}
