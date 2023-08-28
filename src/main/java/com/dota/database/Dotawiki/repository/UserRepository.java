package com.dota.database.Dotawiki.repository;

import com.dota.database.Dotawiki.entity.users.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface UserRepository extends JpaRepository<User, Long> {
    User getUserByName(String name);

    @Query("SELECT CASE WHEN COUNT(u) > 0 THEN true ELSE false END FROM User u WHERE u.name = ?1")
    boolean existsByUserName(String userName);
    boolean existsUsersByEmail(String email);

    User getUserByConfirmationToken(String token);

    User getUserByEmail(String name);

    User getUserByResetToken(String token);

    @Transactional
    @Modifying
    @Query("UPDATE User u SET u.password = ?2 WHERE u.email = ?1")
    void changePassword(String email, String newPassword);

}
