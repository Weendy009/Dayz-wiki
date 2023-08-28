package com.dota.database.Dotawiki.controller;

import com.dota.database.Dotawiki.entity.users.User;
import com.dota.database.Dotawiki.service.RegistrationService;
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
    private final UserService userService;

    @Autowired
    public TokenController(RegistrationService registrationService, UserService userService) {
        this.userService = userService;
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
    @GetMapping("reset/password/{token}")
    public String resetPasswordUser(Model model, @PathVariable String token, HttpServletRequest request) {
        User user = userService.getUserByResetToken(token);
        String email = request.getParameter("email");
        model.addAttribute("email", email);

        if (user == null) {
            return "home";
        }

        return "reset-password";
    }




}
