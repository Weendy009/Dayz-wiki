package com.dota.database.Dotawiki.controller;

import com.dota.database.Dotawiki.service.ArmorService;
import com.dota.database.Dotawiki.service.CarService;
import com.dota.database.Dotawiki.service.MedicineService;
import com.dota.database.Dotawiki.service.WeaponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EquipmentController {
    private final WeaponService weaponService;
    private final ArmorService armorService;
    private final MedicineService medicineService;

    private final CarService carService;

    @Autowired
    public EquipmentController(WeaponService weaponService, ArmorService armorService, MedicineService medicineService, CarService carService) {
        this.weaponService = weaponService;
        this.armorService = armorService;
        this.medicineService = medicineService;
        this.carService = carService;
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

    @GetMapping("/medicaments")
    public String medicaments(Model model) {
        model.addAttribute("pills", medicineService.getAllMedicamentByType("Pills"));
        model.addAttribute("medical_consumables", medicineService.getAllMedicamentByType("medical consumables"));
        model.addAttribute("Injections_and_serums", medicineService.getAllMedicamentByType("Injections and serums"));
        return "medicaments";
    }

    @GetMapping("/cars")
    public String cars(Model model) {
        model.addAttribute("cars", carService.getAllCarsByType("car"));
        return "car";
    }
}
