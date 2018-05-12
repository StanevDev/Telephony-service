package edu.jam.telephony.controller;

import edu.jam.telephony.model.entity.TechSupportUser;
import edu.jam.telephony.service.impl.TechUserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
@RequestMapping("/tech")
public class TechUsersWebController {

    private final TechUserServiceImpl techUserService;

    @Autowired
    public TechUsersWebController(TechUserServiceImpl techUserService) {
        this.techUserService = techUserService;
    }

    @GetMapping("users/list")
    String listAll (Model model) {
        List<TechSupportUser> users = techUserService.getAllUsers();
        var requestsCount = techUserService.getRequestsCountForUsers(users);

        model.addAttribute("users", users);
        model.addAttribute("counts", requestsCount);

        return "tech_users_table";
    }

    @GetMapping ("users/add")
    public String getForm(Model model){
        model.addAttribute("user", new TechSupportUser());
        return "tech_user_form";
    }

    @PostMapping("users/add")
    public RedirectView addUser(@ModelAttribute TechSupportUser user){
        techUserService.addTechUser(user);

        return new RedirectView("/tech/users/list");
    }

}
