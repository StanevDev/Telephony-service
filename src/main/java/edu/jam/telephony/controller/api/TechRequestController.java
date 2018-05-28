package edu.jam.telephony.controller.api;

import edu.jam.telephony.model.entity.TechRequest;
import edu.jam.telephony.service.TechRequestService;
import edu.jam.telephony.service.impl.SubscriberServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("api/tech")
public class TechRequestController {

    @Autowired
    TechRequestService service;
    @Autowired
    SubscriberServiceImpl subService;

    @GetMapping("/get/my")
    @ResponseBody
    List<TechRequest> getSubTechRequest(Principal principal){
        return service.getSubscriberTechRequests(
                subService.fromPrincipal(principal)
        );
    }

    @GetMapping("get/all")
    @ResponseBody
    List<TechRequest> getAllRequests(){
        return service.getAllRequests();
    }

    @PostMapping("add")
    @ResponseBody
    TechRequest addNewTechRequest(@RequestBody String description, Principal principal){
        var sub = subService.fromPrincipal(principal);
        System.out.println(description);
        return service.addTechRequest(description, sub);
    }

}
