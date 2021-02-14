package com.practice3.reporter.Entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "transports")
public class Transport {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long transportId;
    private String transportName;

    @OneToMany(mappedBy = "transport", cascade = CascadeType.ALL)
    private List<Consultation> consultations;
}
