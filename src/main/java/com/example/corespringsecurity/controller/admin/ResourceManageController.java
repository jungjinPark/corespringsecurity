package com.example.corespringsecurity.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ResourceManageController {

    @GetMapping(value = "/admin/resources")
    public String getResources() {

        return "admin/resource/list";
    }
}
