package com.practice3.reporter.Entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "recommendations")
public class Recommendation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long recommendationId;
    private String recommendationName;

    @OneToMany(mappedBy = "recommendation", cascade = CascadeType.ALL)
    private List<Consultation> consultations;
}
