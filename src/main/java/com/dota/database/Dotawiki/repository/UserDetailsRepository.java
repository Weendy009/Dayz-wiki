package com.dota.database.Dotawiki.repository;

import com.dota.database.Dotawiki.entity.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDetailsRepository extends JpaRepository<UserDetails, Long> {
    UserDetails findByUserId(Long userId);

}
