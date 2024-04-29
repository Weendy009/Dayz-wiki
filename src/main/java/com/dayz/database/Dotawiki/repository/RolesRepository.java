package com.dayz.database.Dotawiki.repository;

import com.dayz.database.Dotawiki.entity.users.UserRoles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolesRepository extends JpaRepository<UserRoles, Long> {

}
