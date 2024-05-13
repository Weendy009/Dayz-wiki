package com.dayz.database.Dotawiki.controller;

import com.dayz.database.Dotawiki.service.items.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FoodController {
    private final FoodService foodService;

    @Autowired
    public FoodController(FoodService foodService) {
        this.foodService = foodService;
    }

    @GetMapping("/foods")
    public String viewFoods(Model model) {
        model.addAttribute("others", foodService.getAllByType("other"));
        model.addAttribute("sweets", foodService.getAllByType("sweet"));
        model.addAttribute("canneds", foodService.getAllByType("canned"));
        model.addAttribute("vegetables", foodService.getAllByType("vegetable"));
        model.addAttribute("fruits", foodService.getAllByType("fruit"));
        model.addAttribute("mushrooms", foodService.getAllByType("mushrooms"));
        model.addAttribute("meats", foodService.getAllByType("meat"));
        model.addAttribute("fishes", foodService.getAllByType("fish"));
        model.addAttribute("Water_and_drinks", foodService.getAllByType("Water and drinks"));
        return "foods";
    }

}
