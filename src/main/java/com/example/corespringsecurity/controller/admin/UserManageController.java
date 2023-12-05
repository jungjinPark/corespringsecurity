package com.example.corespringsecurity.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserManageController {

    @GetMapping(value = "/admin/users")
    public String getUsers(Model model) {
//        List<Account> accountList = userService.getUsers();
//        model.addAttribute("users", accountList);

        return "admin/user/list";
    }

}
