package com.dota.database.Dotawiki.entity;

import lombok.Data;

@Data
public class PasswordChangeRequest {
    private String password;
    private String confirmPassword;
    private String email;
}
