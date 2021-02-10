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
    private long id;
    private String surname;
    private String name;
    private String patronymic;

    @OneToMany
    private List<Consultation> consultations;
    @OneToMany
    private List<Consultation> dutyconsultations;

    public Consultant(String surname, String name, String patronymic) {
        this.surname = surname;
        this.name = name;
        this.patronymic = patronymic;
    }

    public String getShortName() {
        return surname + ' ' + name.charAt(0) + ". " + patronymic.charAt(0) + '.'; //Иванов А. И.
    }

    public String getFullName() {
        return surname + ' ' + name + ' ' + patronymic;
    }

    @Override
    public String toString() {
        return "Consultant{" +
                "id=" + id +
                ", surname='" + surname + '\'' +
                ", name='" + name + '\'' +
                ", patronymic='" + patronymic + '\'' +
                '}';
    }
}
