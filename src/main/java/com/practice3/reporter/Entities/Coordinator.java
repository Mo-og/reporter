package com.practice3.reporter.Entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "coordinators")
public class Coordinator {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long coordinatorId;
    @Pattern(regexp = "^[a-zA-Zа-яА-ЯґҐєЄіІїЇ -]{5,100}$", message = "Фамилия может включать только 5-100 символов: латинских или кириллических букв, дефисов [-] и пробелов!")
    private String surname = "";
    @Pattern(regexp = "^[a-zA-Zа-яА-ЯґҐєЄіІїЇ -]{5,100}$", message = "Имя может включать только 5-100 символов: латинских или кириллических букв, дефисов [-] и пробелов!")
    private String name = "";
    @Pattern(regexp = "^[a-zA-Zа-яА-ЯґҐєЄіІїЇ -]{5,100}$", message = "Отчество может включать только 5-100 символов: латинских или кириллических букв, дефисов [-] и пробелов!")
    private String patronymic = "";

    @OneToOne(cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private User user;

    @OneToMany(mappedBy = "coordinator", cascade = CascadeType.ALL)
    private List<Consultation> consultations;

    public Coordinator(String surname, String name, String patronymic) {
        this.surname = surname.trim();
        this.name = name.trim();
        this.patronymic = patronymic.trim();
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
            fullName += patronymic;
        return fullName;
    }

    public void setSurname(String surname) {
        this.surname = surname.trim();
    }

    public void setName(String name) {
        this.name = name.trim();
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic.trim();
    }

    @Override
    public String toString() {
        return "Coordinator{" +
                "id=" + coordinatorId +
                "fullName=" + getFullName() +
                '}';
    }
}
