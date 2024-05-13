package com.dayz.database.Dotawiki.controller;

import com.dayz.database.Dotawiki.service.items.IllnessesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IllnessesController {

    private final IllnessesService illnessesService;

    @Autowired
    public IllnessesController(IllnessesService illnessesService) {
        this.illnessesService = illnessesService;
    }

    @GetMapping("/illnesses")
    public String showIllnesses(Model model) {
        model.addAttribute("illnesses", illnessesService.getAllIllnesses());
        return "illnesses";
    }

}
