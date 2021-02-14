package com.practice3.reporter.Controllers;

import com.practice3.reporter.Entities.*;
import com.practice3.reporter.Services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

@Controller
public class EmergencyController {

    private ConsultantService consultantService;
    private ConsultationService consultationService;
    private CoordinatorService coordinatorService;
    private HospitalService hospitalService;
    private RecommendationService recommendationService;
    private SpecializationService specializationService;
    private TransportService transportService;
    private UserService userService;
    private ReportService reportService;
    private final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");

    @Autowired
    public void setService(ConsultantService service) {
        this.consultantService = service;
    }

    @Autowired
    public void setService(ConsultationService service) {
        this.consultationService = service;
    }

    @Autowired
    public void setService(CoordinatorService service) {
        this.coordinatorService = service;
    }

    @Autowired
    public void setService(HospitalService service) {
        this.hospitalService = service;
    }

    @Autowired
    public void setService(RecommendationService service) {
        this.recommendationService = service;
    }

    @Autowired
    public void setService(SpecializationService service) {
        this.specializationService = service;
    }

    @Autowired
    public void setService(TransportService service) {
        this.transportService = service;
    }

    @Autowired
    public void setService(UserService service) {
        this.userService = service;
    }

    @Autowired
    public void setService(ReportService service) {
        this.reportService = service;
    }

    @GetMapping("/emergency")
    public String giveDateAndPlanned(Model model) {
        Report report = reportService.getRecent();
        if (report != null)
            model.addAttribute("newReport", report);
        else
            model.addAttribute("newReport", new Report());
        model.addAttribute("newConsultation", new Consultation());
        model.addAttribute("newCoordinator", new Coordinator());
        model.addAttribute("newHospital", new Hospital());
        model.addAttribute("hospitals", hospitalService.getAll());
        model.addAttribute("coordinators", coordinatorService.getAll());
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.clear(Calendar.MINUTE);
        calendar.clear(Calendar.SECOND);
        calendar.clear(Calendar.MILLISECOND);
        calendar.set(Calendar.HOUR_OF_DAY, 7);
        model.addAttribute("minDate", formatter.format(calendar.getTime()));
        calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) + 1);
        model.addAttribute("maxDate", formatter.format(calendar.getTime()));
        return "Dispatcher/emergency/datePlannedHospitalCoordinator";
    }


    @PostMapping("/emergency")
    public String proceed(Model model, @RequestParam String date, @ModelAttribute("minDate") String minDate,
                          @Valid Report report, @RequestParam(required = false) BindingResult reportResult,
                          @Valid Consultation consultation, BindingResult consultationResult,
                          @Valid Coordinator coordinator, BindingResult coordinatorResult,
                          @Valid Hospital hospital, BindingResult hospitalResult) {
        //Validation is WIP
        /*if (consultationResult.hasErrors() || coordinatorResult.hasErrors() || hospitalResult.hasErrors()) {
            return "Dispatcher/emergency/datePlannedHospitalCoordinator";
        }
        if (reportResult!=null&&reportResult.hasErrors())return "Dispatcher/emergency/datePlannedHospitalCoordinator";*/
        try {
            consultation.setDate(formatter.parse(date));
        } catch (ParseException e) {
            System.out.println("Не удалось распарсить дату.");
            e.printStackTrace();
        }
        if (hospitalService.getByName(hospital.getName()) == null)
            hospitalService.save(hospital);
        consultation.setHospital(hospitalService.getByName(hospital.getName()));
        if (report != null) {
            Report recentReport = reportService.getRecent();
            if (recentReport != null) {
                if (report.getPlanned() != recentReport.getPlanned()) {
                    recentReport.setPlanned(report.getPlanned());
                    reportService.save(recentReport);
                    consultation.setReport(recentReport);
                }
            } else
                try {
                    GregorianCalendar calendar = new GregorianCalendar();
                    calendar.setTime(formatter.parse(date));
                    calendar.clear(Calendar.MINUTE);
                    calendar.clear(Calendar.SECOND);
                    calendar.clear(Calendar.MILLISECOND);
                    calendar.set(Calendar.HOUR_OF_DAY, 7);
                    report.setDate(calendar.getTime());
                    reportService.save(report);
                    consultation.setReport(report);
                } catch (ParseException e) {
                    System.out.println("\nUNABLE TO SAVE REPORT DATA! [EmergencyController 136:17]\n");
                    e.printStackTrace();
                }
        }
        Coordinator dbCoordinator = coordinatorService.getById(coordinator.getCoordinatorId());
        consultation.setCoordinator(dbCoordinator);
        System.out.println(consultation);
        model.addAttribute("consultation", consultation);
        return "redirect:/consultation";
    }

    @GetMapping("/consultation")
    public String addConsultation(Model model, Consultation consultation) {
        System.out.println(model.getAttribute("consultation"));
        model.addAttribute("consultation", consultation);
        model.addAttribute("newSpecialization", new Specialization());
        model.addAttribute("specializations", specializationService.getAll());
        model.addAttribute("newRecommendation", new Recommendation());
        model.addAttribute("recommendations", recommendationService.getAll());
        model.addAttribute("newTransport", new Transport());
        model.addAttribute("transports", transportService.getAll());
        model.addAttribute("newDuty", new Consultant());
        model.addAttribute("duties", consultantService.getAll());
        return "Dispatcher/emergency/consultation";
    }

    @PostMapping("/consultation")
    public String addingConsultation(Model model,
                                     @Valid Consultation consultation, BindingResult consultationResult,
                                     @Valid Specialization specialization, BindingResult specializationResult,
                                     @Valid Recommendation recommendation, BindingResult recommendationResult,
                                     @Valid Transport transport, BindingResult transportResult,
                                     @Valid Consultant duty, BindingResult dutyResult) {
        if (specializationService.getSpecializationByName(specialization.getSpecializationName()) == null)
            specializationService.save(specialization);
        consultation.setSpecialization(specializationService.getSpecializationByName(specialization.getSpecializationName()));
        if (recommendationService.getRecommendationByName(recommendation.getRecommendationName()) == null)
            recommendationService.save(recommendation);
        consultation.setRecommendation(recommendationService.getRecommendationByName(recommendation.getRecommendationName()));
        if (transportService.getTransportByName(transport.getTransportName()) == null)
            transportService.save(transport);
        consultation.setTransport(transportService.getTransportByName(transport.getTransportName()));
        if (consultantService.getConsultantByName(duty.getName()) == null)
            consultantService.save(duty);
        consultation.setDuty(consultantService.getConsultantByName(duty.getName()));
        System.out.println(consultation);
        model.addAttribute("consultation", consultation);
        return "redirect:/index";
    }
}
