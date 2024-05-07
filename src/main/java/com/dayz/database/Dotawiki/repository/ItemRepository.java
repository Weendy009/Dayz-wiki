package com.dayz.database.Dotawiki.repository;

import com.dayz.database.Dotawiki.entity.items.Item;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ItemRepository {
    Item getItemByIdAndType(Long itemId, String itemType);

    @Query(value = "SELECT name FROM illnesses WHERE name LIKE %:name% " +
            "UNION ALL SELECT name FROM armors WHERE name LIKE %:name% " +
            "UNION ALL SELECT name FROM weapons WHERE name LIKE %:name% " +
            "UNION ALL SELECT name FROM cars WHERE name LIKE %:name% " +
            "UNION ALL SELECT name FROM medicine WHERE name LIKE %:name%", nativeQuery = true)
    List<String> searchAllTables(@Param("name") String name);

}