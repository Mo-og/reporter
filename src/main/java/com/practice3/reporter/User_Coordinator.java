package com.practice3.reporter;

import com.practice3.reporter.Entities.Coordinator;
import com.practice3.reporter.Entities.User;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class User_Coordinator {
    private User user;
    private Coordinator coordinator;

    public User_Coordinator(User user, Coordinator coordinator) {
        this.user=user;
        this.coordinator=coordinator;
    }
}
