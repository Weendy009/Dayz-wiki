package com.dota.database.Dotawiki.service;

import com.dota.database.Dotawiki.entity.UserDetails;

import org.springframework.stereotype.Service;

@Service
public interface UserDetailsService {
    UserDetails findByUserId(Long id);
}
