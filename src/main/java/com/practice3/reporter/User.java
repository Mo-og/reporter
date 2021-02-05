package com.practice3.reporter;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String username;
    private String password;
    private EnumRole role;
    private String surname;
    private String name;
    private String patronymic;

    @OneToMany
    private List<Consultation> consultations;

    public String getShortName() {
        return surname + ' ' + name.charAt(0) + ". " + patronymic.charAt(0) + '.'; //Иванов А. И.
    }

    public String getFullName() {
        return surname + ' ' + name + ' ' + patronymic;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password.substring(0, 5) + "...'" +
                ", roles='" + role + '\'' +
                '}';
    }
}
