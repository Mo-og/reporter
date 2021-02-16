package com.practice3.reporter.Entities;

import com.practice3.reporter.ConsultationSkeleton;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;

@Data
@NoArgsConstructor
@Entity
@Table(name = "consultations")
public class Consultation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long consultationId;
    private Date date;

    @ManyToOne
    @JoinColumn(name = "hospital_id")
    private Hospital hospital;

    @ManyToOne
    @JoinColumn(name = "report_id")
    private Report report;


    @ManyToOne
    @JoinColumn(name = "coordinator_id")
    private Coordinator coordinator;

    @ManyToOne
    @JoinColumn(name = "consultant_id")
    private Consultant consultant;

    @ManyToOne
    @JoinColumn(name = "specialization_id")
    private Specialization specialization;

    @ManyToOne
    @JoinColumn(name = "recommendation_id")
    private Recommendation recommendation;

    @ManyToOne
    @JoinColumn(name = "transport_id")
    private Transport transport;

    @ManyToOne
    @JoinColumn(name = "dutyConsultant_id")
    private Consultant duty;

    @ManyToOne
    @JoinColumn(name = "dutySpecialization_id")
    private Specialization dutySpecialization;

    boolean isCovid;

    public Consultation(Date date, Hospital hospital, Report report, Coordinator coordinator, Consultant consultant, Specialization specialization, Recommendation recommendation, Transport transport, Consultant duty, Specialization dutySpecialization, boolean isCovid) {
        this.date = date;
        this.hospital = hospital;
        this.report = report;
        this.coordinator = coordinator;
        this.consultant = consultant;
        this.specialization = specialization;
        this.recommendation = recommendation;
        this.transport = transport;
        this.duty = duty;
        this.dutySpecialization = dutySpecialization;
        this.isCovid = isCovid;
    }

    private final static SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy HH:mm");
    public String getDateString(){
       return  formatter.format(date);
    }
}
