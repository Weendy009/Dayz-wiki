package com.dota.database.Dotawiki.service.users;

import com.dota.database.Dotawiki.entity.users.User;
import com.dota.database.Dotawiki.entity.users.UserDetails;
import com.dota.database.Dotawiki.repository.UserDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsService {

    private final UserDetailsRepository repository;

    @Autowired
    public UserDetailsService(UserDetailsRepository repository) {
        this.repository = repository;
    }

    public UserDetails findByUserId(Long id) {
        return repository.findByUserId(id);
    }

    public UserDetails getUserDetailsByResetToken(String token) {
        return repository.getUserDetailsByResetToken(token);
    }

    public void save(UserDetails userDetails) {
        repository.save(userDetails);
    }

}
