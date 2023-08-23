package com.dota.database.Dotawiki.service;

import com.dota.database.Dotawiki.entity.UserDetails;
import com.dota.database.Dotawiki.repository.UserDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserDetailsRepository userRepository;

    @Autowired
    public UserDetailsServiceImpl(UserDetailsRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails findByUserId(Long id) {
        return userRepository.findByUserId(id);
    }
}
