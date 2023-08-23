package com.dota.database.Dotawiki.service;

import com.dota.database.Dotawiki.entity.User;
import com.dota.database.Dotawiki.entity.UserDetails;
import com.dota.database.Dotawiki.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository repository;
    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository repository,
                       UserDetailsService userDetailsService,
                       PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

    public boolean isValidUser(String name, String password) {
        if (repository.getUserByName(name) == null) {
            return false;
        }
        if (repository.getUserByName(name).getId() == null) {
            return false;
        }
        User user = repository.getUserByName(name);
        UserDetails details = userDetailsService.findByUserId(user.getId());
        String hashedPassword = user.getPassword();
        return passwordEncoder.matches(password, hashedPassword) && details.isActivatedEmail();
    }

    public boolean existsByUserName(String name) {
        System.out.println("existsByUserName: " + repository.existsByUserName(name));
        return repository.existsByUserName(name);
    }

    public boolean existsUsersByEmail(String email) {
        System.out.println("existsUsersByEmail: " + repository.existsUsersByEmail(email));
        return repository.existsUsersByEmail(email);
    }

    public User getUserByName(String name) {
        return repository.getUserByName(name);
    }
}

