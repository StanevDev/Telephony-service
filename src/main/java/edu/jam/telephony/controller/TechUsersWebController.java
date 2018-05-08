package edu.jam.telephony.controller;

import edu.jam.telephony.service.impl.TechUserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/tech")
public class TechUsersWebController {

    private final TechUserServiceImpl userService;

    @Autowired
    public TechUsersWebController(TechUserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping("users/list")
    String listAll (Model model) {
        var users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "tech_users_table";
    }

}
