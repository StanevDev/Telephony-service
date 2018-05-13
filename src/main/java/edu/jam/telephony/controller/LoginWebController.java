package edu.jam.telephony.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginWebController {

    @GetMapping("/login")
    String getLoginForm(Model model){
        model.addAttribute("error", false);
        model.addAttribute("logout", false);
        return "login";
    }

}
