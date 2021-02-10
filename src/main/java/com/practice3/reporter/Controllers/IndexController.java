package com.practice3.reporter.Controllers;

import com.practice3.reporter.Entities.User;
import com.practice3.reporter.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Controller
public class IndexController {
    private UserService service;

    @Autowired
    public void setService(UserService service) {
        this.service = service;
    }

    @GetMapping("/")
    public String giveIndex() {
        return "index";
    }

    @GetMapping("/login")
    public String giveLogin(Model model) {
        model.addAttribute("form", new User());
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
            model.addAttribute("form", form);
            return "loginForm";
        }
        if (result.hasFieldErrors("password")) {
            model.addAttribute("messagePassword", "Недопустимый пароль!");
            model.addAttribute("passwordFailed", true);
            model.addAttribute("form", form);
            //возможно лишняя нагрузка на базу - проверка существования пользователя когда пароль некорректен:
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
        return "loginForm";
    }

    @PostMapping("/logout")
    public String logOut() {
        return "/index";
    }

    @GetMapping("/logout")
    public String dologOut(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login";
    }

}
