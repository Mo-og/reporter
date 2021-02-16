package com.practice3.reporter.Entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "consultants")
public class Consultant {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long consultantId;
    //    private String surname;
    private String name;
//    private String patronymic;


    public Consultant(String name) {
        this.name = name;
    }

    @OneToMany(mappedBy = "consultant", cascade = CascadeType.ALL)
    private List<Consultation> consultations;
    @OneToMany(mappedBy = "duty", cascade = CascadeType.ALL)
    private List<Consultation> dutyConsultations;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Specialization> specializations;


    public void addSpecialization(Specialization specialization) {
        if (!specializations.contains(specialization))
            specializations.add(specialization);
    }

/*    public String getShortName() {
        return surname + ' ' + name.charAt(0) + ". " + patronymic.charAt(0) + '.'; //Иванов А. И.
    }*/

/*
    public String getFullName() {
        return surname + ' ' + name + ' ' + patronymic;
    }
*/

    @Override
    public String toString() {
        return "Consultant{" +
                "id=" + consultantId +
                // ", surname='" + surname + '\'' +
                ", fullName='" + name + '\'' +
                // ", patronymic='" + patronymic + '\'' +
                '}';
    }
}
