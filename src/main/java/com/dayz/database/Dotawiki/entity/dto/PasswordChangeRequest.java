package com.dayz.database.Dotawiki.entity.dto;

import lombok.Data;

@Data
public class PasswordChangeRequest {
    private String password;
    private String confirmPassword;
    private String email;
}
