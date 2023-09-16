package com.dota.database.Dotawiki.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EquipmentController {
    @GetMapping("/weapons")
    public String weapons() {
        return "weapons";
    }
    @GetMapping("/armors")
    public String armors() {
        return "armors";
    }
    @GetMapping("/backpacks")
    public String backpacks() {
        return "backpacks";
    }
    @GetMapping("/adviсe_equipment")
    public String adviсeEquipment() {
        return "adviсe_equipment";
    }

}
