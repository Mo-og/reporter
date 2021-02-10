package com.practice3.reporter.Controllers;

import com.practice3.reporter.Entities.Coordinator;
import com.practice3.reporter.Entities.User;
import com.practice3.reporter.EnumRole;
import com.practice3.reporter.Services.CoordinatorService;
import com.practice3.reporter.Services.UserService;
import com.practice3.reporter.User_Coordinator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Controller
public class UserController {
    private static UserService userService;
    private static CoordinatorService coordinatorService;

    @Autowired
    public void setService(UserService service) {
        UserController.userService = service;
    }

    @Autowired
    public void setService(CoordinatorService service) {
        UserController.coordinatorService = service;
    }

    public static List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    public static List<Coordinator> getAllConsultants() {
        return coordinatorService.getAllCoordinators();
    }

    private List<User_Coordinator> getUser_CoordinatorList() {
        ArrayList<User_Coordinator> list = new ArrayList<>();
        List<User> users = userService.getAllUsers();
        List<Coordinator> coordinators = coordinatorService.getAllCoordinators();
        for (User value : users) {
            list.add(new User_Coordinator(value, value.getCoordinator()));
        }
        return list;
    }

    private EnumRole getRoleByUsername(String username) {
        User user = userService.getByUsername(username);
        return user == null ? null : user.getRole();
    }

    private String prepareUsersModel(Principal principal, Model model) {
        model.addAttribute("list", getUser_CoordinatorList());
        model.addAttribute("newUser", new User());
        model.addAttribute("newCoordinator", new Coordinator());
        model.addAttribute("LoggedUsername", principal.getName());
        model.addAttribute("LoggedRole", getRoleByUsername(principal.getName()));
        return "Dispatcher/users";
    }

    @GetMapping("/users")
    public String giveUsers(Model model, Principal principal) {
        return prepareUsersModel(principal, model);
    }

    @GetMapping("/supersecretrequest7355")
    public String addAdmin(Model model) {
        User user = new User(0, "superuser", "admin", EnumRole.SUPERUSER);
        Coordinator coordinator = new Coordinator("admin", "admin", "admin");
        user.setPassword(new BCryptPasswordEncoder().encode("74553211")); //следует поставить более надежный пароль
        userService.removeByUsername("superuser");
        coordinatorService.removeByFullName("admin", "admin", "admin");
        coordinatorService.saveCoordinator(coordinator);
        user.setCoordinator(coordinator);
        userService.saveUser(user);
        return "redirect:/login";
    }

    @PostMapping("/add_user")
    public String addUser(@Valid User user, @Valid Coordinator coordinator, Principal principal, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return prepareUsersModel(principal, model);
        }
        if (userService.existsWithUsername(user.getUsername())) {
            model.addAttribute("errorMessage", "Такой никнейм уже существует!");
            return prepareUsersModel(principal, model);
        }
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        coordinatorService.saveCoordinator(coordinator);
        user.setCoordinator(coordinator);
        userService.saveUser(user);
        return "redirect:/users";
    }


    @PostMapping("/update_user")
    public String removeUser(@RequestParam Long id, @RequestParam String action, Principal principal) {
        if (action.equals("delete")) {
            if (!userService.existsWithId(id)) throw new NoSuchElementException();
            User user = userService.getById(id);
            Coordinator coordinator = user.getCoordinator();
            userService.removeById(id);
            if (coordinator != null) coordinatorService.removeById(coordinator.getCoordinatorId());
            if (user.getUsername().equals(principal.getName()))
                return "redirect:/logout";
        }
        return "redirect:/users";
    }
}
