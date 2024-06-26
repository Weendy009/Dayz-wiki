package com.dayz.database.Dotawiki.controller;

import com.dayz.database.Dotawiki.entity.dto.LoginForm;
import com.dayz.database.Dotawiki.entity.dto.PasswordChangeRequest;
import com.dayz.database.Dotawiki.entity.dto.RegistrationRequest;
import com.dayz.database.Dotawiki.entity.dto.ResetPasswordRequest;
import com.dayz.database.Dotawiki.entity.users.User;
import com.dayz.database.Dotawiki.entity.users.UserDetails;
import com.dayz.database.Dotawiki.service.RegistrationService;
import com.dayz.database.Dotawiki.service.users.UserDetailsService;
import com.dayz.database.Dotawiki.service.users.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.Objects;

@Controller
public class UserHandlingController {
    private final RegistrationService registrationService;
    private final UserService userService;
    private final BCryptPasswordEncoder passwordEncoder;
    private final UserDetailsService userDetailsService;

    @Autowired
    public UserHandlingController(UserService userService,
                                  BCryptPasswordEncoder passwordEncoder,
                                  RegistrationService registrationService,
                                  UserDetailsService userDetailsService) {
        this.registrationService = registrationService;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.userDetailsService = userDetailsService;
    }

    @GetMapping("/")
    public String showHomePage(HttpServletRequest request) {
        HttpSession session = request.getSession();
        return "home";
    }

    @PostMapping("/authenticate")
    public ResponseEntity<String> processLogin(@RequestBody LoginForm loginForm, HttpServletRequest request) {
        HttpSession session = request.getSession();
        String name = loginForm.getName();
        String password = loginForm.getPassword();

        if (name.contains("@")) {
            if (userService.isValidUserEmail(name, password)) {
                User user = userService.getUserByEmail(name);
                UserDetails userDetails = userDetailsService.findByUserId(user.getId());
                userDetails.setLastActive(new Date());
                userDetailsService.save(userDetails);
                session.removeAttribute("userId");
                session.setAttribute("userId", user.getId());
                return ResponseEntity.ok("success");
            }
        } else {
            if (userService.isValidUser(name, password)) {
                User user = userService.getUserByUserName(name);
                session.removeAttribute("userId");
                session.setAttribute("userId", user.getId());
                return ResponseEntity.ok("success");
            }
        }
        return ResponseEntity.ok("Invalid username or password");
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody RegistrationRequest userDTO) throws MessagingException {
        if (!Objects.equals(userDTO.getConfirmPassword(), userDTO.getPassword())) {
            return ResponseEntity.ok("Password mismatch");
        }
        if (userDTO.getPassword().length() < 6) {
            return ResponseEntity.ok("Password at least 6 characters");
        }
        String encodedPassword = passwordEncoder.encode(userDTO.getPassword());
        userDTO.setPassword(encodedPassword);

        String email = userDTO.getEmail();
        String userName = userDTO.getName();

        boolean emailExists = userService.existsUsersByEmail(email);
        boolean userNameExists = userService.existsByUserName(userName);

        if (!emailExists && !userNameExists) {
            User user = new User();
            user.setName(userDTO.getName());
            user.setEmail(userDTO.getEmail());
            user.setPassword(encodedPassword);
            registrationService.registerUser(user);
            return ResponseEntity.ok("success");
        } else {
            if (emailExists) {
                return ResponseEntity.ok("This email is already in use on another account");
            }
        }
        return ResponseEntity.ok("This username is already taken");
    }

    @PostMapping("/reset")
    @ResponseBody
    public ResponseEntity<String> resetPassword(@RequestBody ResetPasswordRequest request) throws MessagingException {
        String email = request.getEmail();
        if (!email.contains("@")) {
            return ResponseEntity.ok("please enter a valid email address");
        }
        registrationService.resetPassword(email);
        return ResponseEntity.ok("success");

    }

    @PostMapping("/change")
    public ResponseEntity<String> changePassword(@ModelAttribute PasswordChangeRequest request) {
        User user = userService.getUserByEmail(request.getEmail());
        UserDetails userDetails = userDetailsService.findByUserId(user.getId());
        userDetails.setResetToken(null);
        userService.changePassword(user.getEmail(), request.getConfirmPassword());
        return ResponseEntity.ok("success");
    }

}
