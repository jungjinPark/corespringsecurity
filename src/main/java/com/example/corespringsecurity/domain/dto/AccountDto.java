package com.example.corespringsecurity.domain.dto;

import lombok.Data;

@Data
public class AccountDto {

    private String username;

    private String password;

    private String email;

    private int age;

    private String role;
}
