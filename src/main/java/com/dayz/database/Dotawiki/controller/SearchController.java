package com.dayz.database.Dotawiki.controller;

import com.dayz.database.Dotawiki.entity.items.Armor;
import com.dayz.database.Dotawiki.entity.items.Car;
import com.dayz.database.Dotawiki.entity.items.Medicine;
import com.dayz.database.Dotawiki.entity.items.Weapon;
import com.dayz.database.Dotawiki.service.items.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/search")
public class SearchController {
    private final List<String> itemsNames;

    @Autowired
    public SearchController(ItemService itemService) {
        itemsNames = itemService.getAllItemsNames();
    }

    @PostMapping
    public String handleSearchRequest(@RequestParam("text") String searchText, Model model) {
        List<String> searchResults = new ArrayList<>();

        for (String itemName : itemsNames) {
            if (itemName.toLowerCase().contains(searchText.toLowerCase())) {
                searchResults.add(itemName);
            }
        }
        model.addAttribute("searchResults", searchResults);
        System.out.println(searchResults);
        return "search";
    }

    @GetMapping
    public String getSearchView() {
        return "search";
    }

}
