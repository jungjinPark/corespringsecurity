package com.example.corespringsecurity.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RoleManageController {

    @GetMapping(value = "/admin/roles")
    public String getRoles() {
        return "admin/role/list";
    }
}
