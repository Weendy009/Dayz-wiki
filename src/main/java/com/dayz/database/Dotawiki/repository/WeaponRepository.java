package com.dayz.database.Dotawiki.repository;

import com.dayz.database.Dotawiki.entity.items.Weapon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface WeaponRepository extends JpaRepository<Weapon, Long>, ItemRepository {
    Set<Weapon> getAllByType(String weaponType);
}
