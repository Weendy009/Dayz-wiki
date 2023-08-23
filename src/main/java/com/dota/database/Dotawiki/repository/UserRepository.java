package com.dota.database.Dotawiki.repository;

import com.dota.database.Dotawiki.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long> {
    User getUserByName(String name);

    @Query("SELECT CASE WHEN COUNT(u) > 0 THEN true ELSE false END FROM User u WHERE u.name = ?1")
    boolean existsByUserName(String userName);
    boolean existsUsersByEmail(String email);

    User getUserByConfirmationToken(String token);

}
