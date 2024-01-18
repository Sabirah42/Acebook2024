package com.makersacademy.acebook.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/user-login")
    public String showLoginPage() {
        return "user-login";
    }


}
