package com.dota.database.Dotawiki.controller;

import com.dota.database.Dotawiki.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class TokenController {
    private final RegistrationService registrationService;

    @Autowired
    public TokenController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @GetMapping("/activeToken/{token}")
    public String activateUser(@PathVariable String token) {
        boolean activated = registrationService.activateUserByToken(token);

        if (activated) {
            return "activation-success";
        } else {
            return "activation-failure";
        }
    }


}
