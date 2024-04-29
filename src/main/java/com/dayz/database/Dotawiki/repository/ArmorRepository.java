package com.dayz.database.Dotawiki.repository;

import com.dayz.database.Dotawiki.entity.items.Armor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Set;

@Repository
public interface ArmorRepository extends JpaRepository<Armor, Long>, ItemRepository {
    @Transactional
    @Modifying
    Set<Armor> findByType(String type);

    Armor getArmorByIdAndType(Long itemId, String itemType);
}
