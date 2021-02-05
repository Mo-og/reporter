package com.practice3.reporter.Controllers;

import com.practice3.reporter.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping("/")
    public String giveIndex(Model model) {
        return "index";
    }

    @GetMapping("/login")
    public String giveLogin(Model model) {
        model.addAttribute("user",new User());
        return "loginForm";
    }


}
