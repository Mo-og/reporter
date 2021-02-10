package com.practice3.reporter.Entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "hospitals")
public class Hospital {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long hospitalId;
    private String name;

    @OneToMany(mappedBy = "hospital", cascade = CascadeType.ALL)
    private List<Consultation> consultations;
}
