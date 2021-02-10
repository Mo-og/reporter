package com.practice3.reporter.Entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "coordinators")
public class Coordinator {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String surname = "";
    private String name = "";
    private String patronymic = "";

    @OneToOne
    private User user;

    @OneToMany
    private List<Consultation> consultations;

    public Coordinator(String surname, String name, String patronymic) {
        this.surname = surname;
        this.name = name;
        this.patronymic = patronymic;
    }

    public String getShortName() {
        String shortName = "";
        if (surname != null)
            shortName += surname + " ";
        if (name != null)
            shortName += name.length() < 1 ? "" : name.charAt(0) + ". ";
        if (patronymic != null)
            shortName += patronymic.length() < 1 ? "" : patronymic.charAt(0) + ".";
        return shortName; //Иванов А. И.
    }

    public String getFullName() {
        String fullName = "";
        if (surname != null)
            fullName += surname + " ";
        if (name != null)
            fullName += name + " ";
        if (patronymic != null)
            fullName += patronymic.length() < 1 ? "" : patronymic.charAt(0) + ".";
        return fullName;
    }

    public void setSurname(String surname) {
        this.surname = surname.strip();
    }

    public void setName(String name) {
        this.name = name.strip();
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic.strip();
    }

    @Override
    public String toString() {
        return "Coordinator{" +
                "id=" + id +
                "fullName=" + getFullName() +
                '}';
    }
}
