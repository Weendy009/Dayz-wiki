package com.dota.database.Dotawiki.controller;

import com.dota.database.Dotawiki.service.RegistrationService;
import com.dota.database.Dotawiki.service.users.UserDetailsService;
import com.dota.database.Dotawiki.service.users.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;

@Controller
public class TokenController {
    private final RegistrationService registrationService;
    private final UserDetailsService userDetailsService;

    @Autowired
    public TokenController(RegistrationService registrationService, UserDetailsService userDetailsService) {
        this.registrationService = registrationService;
        this.userDetailsService = userDetailsService;
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
    @GetMapping("reset/password/{token}")
    public String resetPasswordUser(Model model, @PathVariable String token, HttpServletRequest request) {
        if (userDetailsService.getUserDetailsByResetToken(token) != null) {
            String email = request.getParameter("email");
            model.addAttribute("email", email);

            return "reset-password";
        } else {
            return "home";
        }

    }




}
