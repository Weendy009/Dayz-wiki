package com.dayz.database.Dotawiki.repository;

import com.dayz.database.Dotawiki.entity.users.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDetailsRepository extends JpaRepository<UserDetails, Long> {
    UserDetails findByUserId(Long userId);

    UserDetails getUserDetailsByResetToken(String token);

    UserDetails getUserDetailsByConfirmationToken(String token);
}
