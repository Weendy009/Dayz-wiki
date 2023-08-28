package com.dota.database.Dotawiki.entity.controllerRequests;

import lombok.Data;

@Data
public class RegistrationRequest {
    private String name;
    private String email;
    private String password;
    private String confirmPassword;
}
