package com.practice3.reporter.Controllers;

import com.practice3.reporter.EnumRole;
import com.practice3.reporter.Services.UserService;
import com.practice3.reporter.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Random;

@Controller
public class IndexController {
    UserService service;

    @Autowired
    public void setService(UserService service) {
        this.service = service;
    }

    @GetMapping("/")
    public String giveIndex(Model model) {
        return "index";
    }

    @GetMapping("/users")
    public String giveUsers(Model model) {
        /*Random random=new Random();
        for (int i = 0; i < random.nextInt(32); i++) {
            service.saveUser(new User("user"+random.nextInt(), "123456", random.nextBoolean() ? EnumRole.ROLE_DISPATCHER : EnumRole.ROLE_SUPERUSER));
        }*/
        model.addAttribute("list", service.getAllUsers());
        return "users";
    }

    @GetMapping("/login")
    public String giveLogin(Model model) {
        model.addAttribute("user",new User());
        return "loginForm";
    }


}
