package com.dota.database.Dotawiki.repository;

import com.dota.database.Dotawiki.entity.items.Armor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface ArmorRepository extends JpaRepository<Armor, Long> {
    @Transactional
    @Modifying
    List<Armor> findByType(String type);
}
