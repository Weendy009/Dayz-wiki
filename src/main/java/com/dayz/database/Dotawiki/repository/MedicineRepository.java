package com.dayz.database.Dotawiki.repository;

import com.dayz.database.Dotawiki.entity.items.Medicine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedicineRepository extends JpaRepository<Medicine, Long>, ItemRepository {

    public List<Medicine> getMedicinesByType(String type);

}
