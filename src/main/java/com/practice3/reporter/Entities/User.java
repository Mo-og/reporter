package com.practice3.reporter.Entities;

import com.practice3.reporter.EnumRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Random;

@Data
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Pattern(regexp = "^[a-zA-Z0-9_.-]{5,125}$", message = "Никнейм должен состоять только из 5-125 символов: цифр [0-9], латинских букв [A-z], точек [.], дефисов [-] и знаков нижнего подчеркивания [_]!")
    private String username = "";
    @Size(min = 5, max = 100, message = "Пароль должен быть в переделах 5-100 символов!")
    private String password = "";
    private EnumRole role = EnumRole.DISPATCHER;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "coordinator_id")
    private Coordinator coordinator;

    public User(String username, String password, EnumRole role) {
        this.username = username.trim();
        this.password = password.trim();
        this.role = role;
    }

    public String getUsername() {
        if (username == null || username.isEmpty())
            return "";
        return username;
    }

    public String getShortPassword() {
        return (password.length() < 15 ? password : password.substring(0, 15) + "...");
    }

    public void setUsername(String username) {
        this.username = username.trim();
    }

    public void setPassword(String password) {
        this.password = password.trim();
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
