package com.dota.database.Dotawiki.controller;

import com.dota.database.Dotawiki.service.ArmorService;
import com.dota.database.Dotawiki.service.WeaponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EquipmentController {
    private final WeaponService weaponService;
    private final ArmorService armorService;
    @Autowired
    public EquipmentController(WeaponService weaponService, ArmorService armorService) {
        this.weaponService = weaponService;
        this.armorService = armorService;
    }

    @GetMapping("/weapons")
    public String showHomePage(Model model) {
        model.addAttribute("steelArms", weaponService.getAllSteelArms());
        model.addAttribute("pistols", weaponService.getAllPistols());
        model.addAttribute("submachineGuns", weaponService.getAllSubmachineGuns());
        return "weapons";
    }
    @GetMapping("/equipment")
    public String armors(Model model) {
        model.addAttribute("shoes", armorService.getAllShoes());
        model.addAttribute("masks", armorService.getAllMasks());
        model.addAttribute("hats", armorService.getAllHats());
        model.addAttribute("glasses", armorService.getAllGlasses());
        model.addAttribute("gloves", armorService.getAllGloves());
        model.addAttribute("vests", armorService.getAllVests());
        model.addAttribute("backpacks", armorService.getAllBackpacks());
        model.addAttribute("cloth", armorService.getAllCloth());
        model.addAttribute("belts", armorService.getAllBelts());
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
