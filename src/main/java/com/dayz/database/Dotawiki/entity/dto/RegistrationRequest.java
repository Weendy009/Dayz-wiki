package com.dayz.database.Dotawiki.entity.dto;

import lombok.Data;

@Data
public class RegistrationRequest {
    private String name;
    private String email;
    private String password;
    private String confirmPassword;
}
