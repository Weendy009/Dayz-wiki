package com.dota.database.Dotawiki.repository;

import com.dota.database.Dotawiki.entity.items.Medicine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedicineRepository extends JpaRepository<Medicine, Long> {

    public List<Medicine> getMedicinesByType(String type);

}
