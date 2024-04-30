package com.dayz.database.Dotawiki.repository;

import com.dayz.database.Dotawiki.entity.items.Armor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface ArmorRepository extends JpaRepository<Armor, Long>, ItemRepository {
    Set<Armor> getArmorsByType(String type);
}
