package com.dayz.database.Dotawiki.repository;

import com.dayz.database.Dotawiki.entity.items.*;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ItemRepository {

    @Query("SELECT a FROM Armor a WHERE a.id = :id and a.type = :type")
    Armor getArmorByIdAndType(Long id, String type);

    @Query("SELECT w FROM Weapon w WHERE w.id = :id and w.type = :type")
    Weapon getWeaponByIdAndType(Long id, String type);

    @Query("SELECT c FROM Car c WHERE c.id = :id and c.type = :type")
    Car getCarByIdAndType(Long id, String type);

    @Query("SELECT f FROM Food f WHERE f.id = :id and f.type = :type")
    Food getFoodByIdAndType(Long id, String type);

    @Query("SELECT i FROM Illnesses i WHERE i.id = :id and i.type = :type")
    Illnesses getIllnessesByIdAndType(Long id, String type);

    @Query("SELECT m FROM Medicine m WHERE m.id = :id and m.type = :type")
    Medicine getMedicineByIdAndType(Long id, String type);

    @Query(value = "SELECT name FROM illnesses WHERE name LIKE %:name% " +
            "UNION ALL SELECT name FROM armors WHERE name LIKE %:name% " +
            "UNION ALL SELECT name FROM weapons WHERE name LIKE %:name% " +
            "UNION ALL SELECT name FROM cars WHERE name LIKE %:name% " +
            "UNION ALL SELECT name FROM medicine WHERE name LIKE %:name% " +
            "UNION ALL SELECT name FROM foods WHERE name LIKE %:name%", nativeQuery = true)
    List<String> searchAllTables(@Param("name") String name);

}