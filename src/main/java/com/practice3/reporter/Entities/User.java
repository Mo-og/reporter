package com.practice3.reporter.Entities;

import com.practice3.reporter.EnumRole;
import lombok.AllArgsConstructor;
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
    private String username = "";
    private String password = "";
    private EnumRole role = EnumRole.DISPATCHER;

    @OneToOne
    private Coordinator coordinator;

    public User(long id, String username, String password, EnumRole role) {
        this.username = username.strip();
        this.password = password.strip();
        this.role = role;
    }

    public String getUsername() {
        if(username==null||username.isEmpty())
            return "";
        return username;
    }

    public String getShortPassword() {
        return (password.length() < 15 ? password : password.substring(0, 15) + "...");
    }

    public void setUsername(String username) {
        this.username = username.strip();
    }

    public void setPassword(String password) {
        this.password = password.strip();
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + (password.length() < 15 ? "" : password.substring(0, 15) + "...'") +
                ", roles='" + role + '\'' +
                '}';
    }
}
