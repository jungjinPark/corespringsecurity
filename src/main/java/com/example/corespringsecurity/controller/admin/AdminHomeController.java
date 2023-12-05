package com.example.corespringsecurity.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminHomeController {

    @GetMapping(value = "/admin")
    public String getAdminHome() {
        return "admin/home";
    }
}
