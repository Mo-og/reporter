package com.practice3.reporter.Entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "specializations")
public class Specialization {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long specializationId;
    private String specializationName;

    @OneToMany(mappedBy = "specialization", cascade = CascadeType.ALL)
    private List<Consultation> consultations;

    @ManyToMany(mappedBy = "specializations")
    private List<Consultant> consultants;
}
