package com.practice3.reporter.Controllers;

import com.practice3.reporter.ConsultationSkeleton;
import com.practice3.reporter.Entities.*;
import com.practice3.reporter.Services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;

import java.text.ParsePosition;
import java.util.Date;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.security.Principal;
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
    public String giveDateAndPlanned(Model model, Principal principal) {
        Report report = reportService.getRecent();
        if (report != null)
            model.addAttribute("newReport", report);
        else
            model.addAttribute("newReport", new Report());
        model.addAttribute("LoggedId", userService.getByUsername(principal.getName()).getCoordinator().getCoordinatorId());
        model.addAttribute("newHospital", new Hospital());
        model.addAttribute("hospitals", hospitalService.getAll());
        model.addAttribute("coordinators", coordinatorService.getAll());
        model.addAttribute("consultants", consultantService.getAll());
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
    public String proceed(Model model, @RequestParam String date,
                          @Valid Report report, @RequestParam(required = false) BindingResult reportResult,
                          @Valid Consultation consultation, BindingResult consultationResult,
                          @Valid Long coordinatorId, BindingResult coordinatorResult,
                          @Valid Hospital hospital, BindingResult hospitalResult,
                          String consultant, String duty) {
        //Validation is WIP
        /*if (consultationResult.hasErrors() || coordinatorResult.hasErrors() || hospitalResult.hasErrors()) {
            return "Dispatcher/emergency/datePlannedHospitalCoordinator";
        }
        if (reportResult!=null&&reportResult.hasErrors())return "Dispatcher/emergency/datePlannedHospitalCoordinator";*/
        ConsultationSkeleton skeleton = new ConsultationSkeleton();
        skeleton.setDate(date);

        if (hospitalService.getByName(hospital.getName()) == null)
            hospitalService.save(hospital);
        skeleton.setHospitalId(hospitalService.getByName(hospital.getName()).getHospitalId());
        if (consultantService.getConsultantByName(consultant) == null) {
            consultantService.save(new Consultant(consultant));
        }
        skeleton.setConsultantId(consultantService.getConsultantByName(consultant).getConsultantId());
        if (consultantService.getConsultantByName(duty) == null)
            consultantService.save(new Consultant(duty));
        skeleton.setDutyId(consultantService.getConsultantByName(duty).getConsultantId());
        Report recentReport = reportService.getRecent();
        if (recentReport != null) {
            skeleton.setReportId(recentReport.getId());
            if (report != null)
                if (report.getPlanned() != recentReport.getPlanned()) {
                    recentReport.setPlanned(report.getPlanned());
                    reportService.save(recentReport);
                    skeleton.setReportId(recentReport.getId());
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
                skeleton.setReportId(report.getId());
            } catch (ParseException e) {
                System.out.println("\nUNABLE TO SAVE REPORT DATA! [EmergencyController {proceed method}]\n");
                e.printStackTrace();
            }

        Coordinator dbCoordinator = coordinatorService.getById(coordinatorId);
        skeleton.setCoordinatorId(dbCoordinator.getCoordinatorId());
//        System.out.println("\n\nCONSULTATION: "+consultation+"\n\n");
        model.addAttribute("consultation", skeleton);
        model.addAttribute("newSpecialization", new Specialization());
        model.addAttribute("specializations", specializationService.getAllForDoctorId(skeleton.getConsultantId()));
        model.addAttribute("dutySpecializations", specializationService.getAllForDoctorId(skeleton.getDutyId()));
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
                                     @Valid ConsultationSkeleton skeleton, BindingResult consultationResult,
                                     @Valid Specialization specialization, BindingResult specializationResult,
                                     @Valid Recommendation recommendation, BindingResult recommendationResult,
                                     @Valid Transport transport, BindingResult transportResult,
                                     @Valid Consultant duty, BindingResult dutyResult,
                                     boolean isCovid, String dutySpecialization) {
        if (specializationService.getSpecializationByName(specialization.getSpecializationName()) == null) {
            specializationService.save(specialization);
        }
        Specialization dbSpecialization=specializationService.getSpecializationByName(specialization.getSpecializationName());
        skeleton.setSpecializationId(dbSpecialization.getSpecializationId());
        if (specializationService.getSpecializationByName(dutySpecialization) == null) {
            specializationService.save(new Specialization(dutySpecialization));
        }
        Specialization dbDutySpecialization=specializationService.getSpecializationByName(specialization.getSpecializationName());
        skeleton.setDutySpecializationId(dbDutySpecialization.getSpecializationId());
        Consultant dbConsultant=consultantService.getById(skeleton.getConsultantId());
        dbConsultant.addSpecialization(dbSpecialization);
        consultantService.save(dbConsultant);
        if (recommendationService.getRecommendationByName(recommendation.getRecommendationName()) == null)
            recommendationService.save(recommendation);
        skeleton.setRecommendationId(recommendationService.getRecommendationByName(recommendation.getRecommendationName()).getRecommendationId());
        if (transportService.getTransportByName(transport.getTransportName()) == null)
            transportService.save(transport);
        skeleton.setTransportId(transportService.getTransportByName(transport.getTransportName()).getTransportId());

        skeleton.setCovid(isCovid);
        System.out.println(skeleton);
        try {
            consultationService.save(
                    new Consultation(
                            formatter.parse(skeleton.getDate()),
                            hospitalService.getById(skeleton.getHospitalId()),
                            reportService.getById(skeleton.getReportId()),
                            coordinatorService.getById(skeleton.getCoordinatorId()),
                            consultantService.getById(skeleton.getConsultantId()),
                            specializationService.getById(skeleton.getSpecializationId()),
                            recommendationService.getById(skeleton.getRecommendationId()),
                            transportService.getById(skeleton.getTransportId()),
                            consultantService.getById(skeleton.getDutyId()),
                            specializationService.getById(skeleton.getDutySpecializationId()),
                            isCovid));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        model.addAttribute("consultation", skeleton);
        return "redirect:/report";
    }
    @GetMapping("/report")
    public String giveReport(Model model){
        model.addAttribute("list",consultationService.getAll());
        return "Dispatcher/report";
    }
}
