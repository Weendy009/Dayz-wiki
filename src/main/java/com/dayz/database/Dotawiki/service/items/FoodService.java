package com.dayz.database.Dotawiki.service.items;

import com.dayz.database.Dotawiki.entity.items.Food;
import com.dayz.database.Dotawiki.repository.FoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoodService {
    private final FoodRepository foodRepository;

    @Autowired
    public FoodService(FoodRepository foodRepository) {
        this.foodRepository = foodRepository;
    }

    public List<Food> getAllByType(String type) {
        return foodRepository.findAllByType(type);
    }
}
