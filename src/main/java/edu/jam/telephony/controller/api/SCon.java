package edu.jam.telephony.controller.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/api")
public class SCon {

    @GetMapping("/hui")
    String some(Principal principal){
        return "Hui " + principal.getName();
    }

}
