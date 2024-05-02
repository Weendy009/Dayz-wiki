package com.dayz.database.Dotawiki.controller;

import com.dayz.database.Dotawiki.service.items.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<List<String>> handleSearchRequest(@RequestParam("text") String searchText) {
        List<String> searchResults = new ArrayList<>();
        for (String itemName : itemsNames) {
            if (itemName.toLowerCase().contains(searchText.toLowerCase())) {
                searchResults.add(itemName);
            }
        }
        return ResponseEntity.ok(searchResults);
    }

}
