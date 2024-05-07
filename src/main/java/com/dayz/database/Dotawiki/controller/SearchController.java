package com.dayz.database.Dotawiki.controller;

import com.dayz.database.Dotawiki.service.items.ItemService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/search")
public class SearchController {
    private final ItemService itemService;

    public SearchController(ItemService itemService) {
        this.itemService = itemService;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<List<String>> handleSearchRequest(@RequestParam("text") String searchText) {
        return ResponseEntity.ok(itemService.searchByAllNames(searchText));
    }

}
