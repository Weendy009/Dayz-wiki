package com.dota.database.Dotawiki.repository;

import com.dota.database.Dotawiki.entity.items.Weapon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Set;

@Repository
public interface WeaponRepository extends JpaRepository<Weapon, Long> {
    @Transactional
    @Modifying
    Set<Weapon> findByType(String type);

    Weapon getWeaponByIdAndType(Long weaponId, String weaponType);
}
