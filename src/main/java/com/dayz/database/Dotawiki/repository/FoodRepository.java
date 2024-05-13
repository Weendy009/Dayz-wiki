package com.dayz.database.Dotawiki.repository;


import com.dayz.database.Dotawiki.entity.items.Food;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FoodRepository extends JpaRepository<Food, Long>, ItemRepository {
    List<Food> findAllByType(String type);
}
