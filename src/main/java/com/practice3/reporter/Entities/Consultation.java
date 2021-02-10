package com.practice3.reporter.Entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@Entity
@Table(name = "consultations")
public class Consultation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    private Hospital hospital;

    private Date date;

    @ManyToOne
    private User user;

    @ManyToOne
    private Consultant consultant;

    @ManyToOne
    private Specialization doctorSpecialization;

    @ManyToOne
    private Recommendation recommendation;
    private String comment;

    @ManyToOne
    private Transport transport;

    @ManyToOne
    private Consultant duty;

    @ManyToOne
    private Specialization dutySpecialization;

    boolean isCovid;

}
