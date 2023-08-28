package com.dota.database.Dotawiki.service.users;

import com.dota.database.Dotawiki.entity.users.UserDetails;
import com.dota.database.Dotawiki.repository.UserDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsService {

    private final UserDetailsRepository userRepository;

    @Autowired
    public UserDetailsService(UserDetailsRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDetails findByUserId(Long id) {
        return userRepository.findByUserId(id);
    }



}
