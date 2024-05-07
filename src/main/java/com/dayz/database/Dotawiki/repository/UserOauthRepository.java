package com.dayz.database.Dotawiki.repository;

import com.dayz.database.Dotawiki.entity.users.UserOauth;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserOauthRepository extends JpaRepository<UserOauth, String> {
}
