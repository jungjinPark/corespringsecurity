package com.example.corespringsecurity.controller.user;

import com.example.corespringsecurity.domain.dto.AccountDto;
import com.example.corespringsecurity.domain.entity.Account;
import com.example.corespringsecurity.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping(value = "/users")
    public String getUserList(Model model) {
        return "user/list";
    }

    @GetMapping(value="/user/register")
    public String createUser() {
        return "user/register";
    }

    @PostMapping(value="/user/register")
    public String createUser(AccountDto accountDto) {
        ModelMapper modelMapper = new ModelMapper();

        Account account = modelMapper.map(accountDto, Account.class);
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        account.setRole("ROLE_USER");

        userService.createUser(account);

        return "redirect:/";
    }
}
