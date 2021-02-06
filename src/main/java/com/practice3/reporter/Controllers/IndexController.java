package com.practice3.reporter.Controllers;

import com.practice3.reporter.Entities.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class IndexController {

    @GetMapping("/")
    public String giveIndex(Model model) {
        return "index";
    }

    @GetMapping("/login")
    public String giveLogin(Model model) {
        model.addAttribute("form",new User());
        return "loginForm";
    }
    @PostMapping("/login")
    public String authenticate(Model model, @Valid User form, BindingResult result) {
        if (result.hasErrors())
            return "Dispatcher/users";
        model.addAttribute("form", form);
        model.addAttribute("list", UserController.getAllUsers());
        model.addAttribute("newUser", new User());
        return "Dispatcher/users";
    }


}
