package edu.jam.telephony.controller.api;

import edu.jam.telephony.model.entity.Service;
import edu.jam.telephony.model.entity.ServiceType;
import edu.jam.telephony.service.impl.ServiceService;
import edu.jam.telephony.service.impl.SubscriberServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/service")
public class ServiceController {

    @Autowired
    SubscriberServiceImpl subService;
    @Autowired
    ServiceService serviceService;

    @GetMapping("/get")
    @ResponseBody
    List<Service> getAllServices() {
        return serviceService.getAllServices();
    }

    @GetMapping("/get/my")
    @ResponseBody
    List<Service> getExtraServicesBySubscriber(Principal principal){
        var sub = subService.fromPrincipal(principal);

        return serviceService.getServicesBySubscriber(sub);
    }

}
