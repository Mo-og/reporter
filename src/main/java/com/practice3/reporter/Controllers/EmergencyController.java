package com.practice3.reporter.Controllers;

import com.practice3.reporter.Entities.*;
import com.practice3.reporter.Services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EmergencyController {

    private ConsultantService consultantService;
    private ConsultationService consultationService;//
    private CoordinatorService coordinatorService;
    private HospitalService hospitalService;//
    private RecommendationService recommendationService;//
    private SpecializationService specializationService;//
    private TransportService transportService;//
    private UserService userService;

    @Autowired
    public void setService(ConsultantService service){
        this.consultantService=service;
    }
    @Autowired
    public void setService(ConsultationService service){
        this.consultationService=service;
    }
    @Autowired
    public void setService(CoordinatorService service){
        this.coordinatorService=service;
    }
    @Autowired
    public void setService(HospitalService service){
        this.hospitalService=service;
    }
    @Autowired
    public void setService(RecommendationService service){
        this.recommendationService=service;
    }
    @Autowired
    public void setService(SpecializationService service){
        this.specializationService=service;
    }
    @Autowired
    public void setService(TransportService service){
        this.transportService=service;
    }
    @Autowired
    public void setService(UserService service){
        this.userService=service;
    }

    @GetMapping("/emergency")
    public String giveDateAndPlanned(Model model) {
        model.addAttribute("newReport", new Report());
        return "Dispatcher/emergency/dateAndPlanned";
    }
}
