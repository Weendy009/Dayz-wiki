package com.dayz.database.Dotawiki.controller;

import com.dayz.database.Dotawiki.service.RegistrationService;
import com.dayz.database.Dotawiki.service.users.UserDetailsService;
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
        return activated ? "activation-success" : "activation-failure";
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
