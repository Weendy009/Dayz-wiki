package com.dayz.database.Dotawiki.controller;

import com.dayz.database.Dotawiki.service.items.ArmorService;
import com.dayz.database.Dotawiki.service.items.CarService;
import com.dayz.database.Dotawiki.service.items.MedicineService;
import com.dayz.database.Dotawiki.service.items.WeaponService;
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
        model.addAttribute("steelArms", weaponService.getAllWeaponsByType("steel arms"));
        model.addAttribute("pistols", weaponService.getAllWeaponsByType("pistol"));
        model.addAttribute("submachineGuns", weaponService.getAllWeaponsByType("submachine guns"));
        model.addAttribute("rifles", weaponService.getAllWeaponsByType("rifle"));
        model.addAttribute("shotguns", weaponService.getAllWeaponsByType("shotgun"));
        return "weapons";
    }
    @GetMapping("/equipment")
    public String armors(Model model) {
        model.addAttribute("shoes", armorService.getAllArmorByType("shoes"));
        model.addAttribute("masks", armorService.getAllArmorByType("mask"));
        model.addAttribute("hats", armorService.getAllArmorByType("hats"));
        model.addAttribute("glasses", armorService.getAllArmorByType("glasses"));
        model.addAttribute("gloves", armorService.getAllArmorByType("gloves"));
        model.addAttribute("vests", armorService.getAllArmorByType("vests"));
        model.addAttribute("backpacks", armorService.getAllArmorByType("backpacks"));
        model.addAttribute("cloth", armorService.getAllArmorByType("cloth"));
        model.addAttribute("belts", armorService.getAllArmorByType("belts"));
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
