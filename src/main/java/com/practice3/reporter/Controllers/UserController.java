package com.practice3.reporter.Controllers;

import com.practice3.reporter.Services.UserService;
import com.practice3.reporter.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.NoSuchElementException;

@Controller
public class UserController {
    private UserService service;

    @Autowired
    public void setService(UserService service) {
        this.service = service;
    }

    @GetMapping("/users")
    public String giveUsers(Model model) {
        /*Random random=new Random();
        for (int i = 0; i < random.nextInt(32); i++) {
            service.saveUser(new User("user"+random.nextInt(), "123456", random.nextBoolean() ? EnumRole.ROLE_DISPATCHER : EnumRole.ROLE_SUPERUSER));
        }*/
        model.addAttribute("list", service.getAllUsers());
        model.addAttribute("newUser", new User());
        return "users";
    }

    @PostMapping("/add_user")
    public String addUser(User user, Model model) {
        service.saveUser(user);
        model.addAttribute("list", service.getAllUsers());
        model.addAttribute("newUser", new User());
        return "users";
    }

    @GetMapping("/remove_user")
    public String removeUser(@RequestParam Long id) {
        if (!service.existsWithId(id))
            throw new NoSuchElementException();
        service.removeById(id);
        return "redirect:/users";
    }
}
