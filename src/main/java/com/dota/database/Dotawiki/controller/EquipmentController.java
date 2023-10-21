package com.dota.database.Dotawiki.controller;

import com.dota.database.Dotawiki.service.WeaponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EquipmentController {
    private final WeaponService weaponService;
    @Autowired
    public EquipmentController(WeaponService weaponService) {
        this.weaponService = weaponService;
    }

    @GetMapping("/weapons")
    public String showHomePage(Model model) {
        model.addAttribute("steelArms", weaponService.getAllSteelArms());
        model.addAttribute("pistols", weaponService.getAllPistols());
        model.addAttribute("submachineGuns", weaponService.getAllSubmachineGuns());
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
