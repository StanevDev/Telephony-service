package edu.jam.telephony.controller;

import edu.jam.telephony.dao.impl.TariffPlanDaoImpl;
import edu.jam.telephony.model.entity.TariffPlan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/tariffs")
public class TariffsWebController {

    final TariffPlanDaoImpl dao;
    List<String> services = new ArrayList<String>(){{
        add("Service 1");
        add("Service 2");
        add("Service 3");
    }};

    @Autowired
    public TariffsWebController(TariffPlanDaoImpl dao) {
        this.dao = dao;
    }

    @GetMapping("/list")
    public String list(Model model){
        List<TariffPlan> tariffs = dao.getAll();
        model.addAttribute("tariffs", tariffs);
        return "tariffs_page";
    }

    @GetMapping ("/add")
    public String tariffForm(HttpSession session, Model model){
        model.addAttribute("tariff", new TariffPlan());
        model.addAttribute("services", services);
        return "tariff_form";
    }

    @PostMapping("/add")
    public String addTariffPlan (@ModelAttribute TariffPlan tariffPlan,
                                 Model model) {

        return "index";
    }
}
