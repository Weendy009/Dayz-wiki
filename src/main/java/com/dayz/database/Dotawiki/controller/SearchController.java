package com.dayz.database.Dotawiki.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/search")
public class SearchController {
    @GetMapping
    public String getSearchView() {
        return "search";
    }

    @PostMapping
    public String handleSearchRequest(@RequestParam("text") String searchText) {
        System.out.println(searchText);
        return "search";
    }

}
