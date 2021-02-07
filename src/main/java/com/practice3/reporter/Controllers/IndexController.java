package com.practice3.reporter.Controllers;

import com.practice3.reporter.Entities.User;
import com.practice3.reporter.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class IndexController {
    private UserService service;
    @Autowired
    public void setService(UserService service){this.service=service;}

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
        if (result.hasFieldErrors("username")) {
            model.addAttribute("messageUsername", "Некорректное имя пользователя!");
            model.addAttribute("usernameFailed", true);
            if (result.hasFieldErrors("password")) {
                model.addAttribute("messagePassword", "Недопустимый пароль!");
                model.addAttribute("passwordFailed", true);
            }
            return "loginForm";
        }
        if (result.hasFieldErrors("password")) {
            model.addAttribute("messagePassword", "Недопустимый пароль!");
            model.addAttribute("passwordFailed", true);
            //возможно лишняя нагрузка на базу:
            /*if (!service.existsWithUsername(form.getUsername())) {
                model.addAttribute("messageUsername", "Пользователь не найден");
                model.addAttribute("usernameFailed", true);
            }*/
            return "loginForm";
        }
        if (service.existsWithUsername(form.getUsername())) {
            model.addAttribute("messagePassword", "Введён неверный пароль!");
            model.addAttribute("passwordFailed", true);
        } else {
            model.addAttribute("messageUsername", "Пользователь не найден");
            model.addAttribute("usernameFailed", true);
        }
        model.addAttribute("form", form);
        model.addAttribute("list", UserController.getAllUsers());
        model.addAttribute("newUser", new User());
        return "loginForm";
    }


}
