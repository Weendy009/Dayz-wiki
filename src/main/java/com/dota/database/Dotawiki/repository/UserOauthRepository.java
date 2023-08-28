package com.dota.database.Dotawiki.repository;

import com.dota.database.Dotawiki.entity.users.UserOauth;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserOauthRepository extends JpaRepository<UserOauth, String> {
}
