package com.example.redis.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("auth")
public class AuthController {
    @GetMapping("login")
    public String loginForm() {
        return "login-form";
    }

    @GetMapping("my-profile")
    public String myProfile() {
        return "my-profile";
    }

}
