package com.dota.database.Dotawiki.entity.controllerRequests;

import lombok.Data;

@Data
public class PasswordChangeRequest {
    private String password;
    private String confirmPassword;
    private String email;
}
