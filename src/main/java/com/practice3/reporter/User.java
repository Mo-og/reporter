package com.practice3.reporter;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Optional;

@Data
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String username = "";
    private String password = "";
    private EnumRole role = EnumRole.ROLE_DISPATCHER;
    private String surname = "";
    private String name = "";
    private String patronymic = "";

    @OneToMany
    private List<Consultation> consultations;

    public String getShortName() {
        if (surname == null || name == null || patronymic == null)
            return "";
        return surname + " " + (name.length() < 1 ? "" : name.charAt(0)+ ". ")  + (patronymic.length() < 1 ? "" : patronymic.charAt(0)+ ".") ; //Иванов А. И.
    }

    public String getFullName() {
        return surname + " " + name + " " + patronymic;
    }


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + (password.length() < 5 ? "" : password.substring(0, 5)+ "...'")  +
                ", roles='" + role + '\'' +
                '}';
    }
}
