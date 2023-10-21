package com.dota.database.Dotawiki.controller;

import com.dota.database.Dotawiki.entity.controllerRequests.FilterRequest;
import com.dota.database.Dotawiki.entity.items.Weapon;
import com.dota.database.Dotawiki.service.WeaponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class BtnWeaponsController {
    private final WeaponService service;

    @Autowired
    public BtnWeaponsController(WeaponService service) {
        this.service = service;
    }

    @PostMapping("/weapons/filter")
    public List<Weapon> weight (@RequestBody FilterRequest filterRequest) {
        List<Weapon> allWeapons = new ArrayList<>();
        Integer weaponCategory = filterRequest.getWeaponCategory();
        if (filterRequest.getWeaponCategory() == 0) {
            allWeapons = service.getAllSteelArms();
        } else if (weaponCategory == 1) {
            allWeapons = service.getAllPistols();
        } else if (weaponCategory == 2) {
            allWeapons = service.getAllSubmachineGuns();
        }
        System.out.println(filterRequest.getWeightFilters());
        System.out.println(filterRequest.getTierFilters());
        List<Weapon> filteredWeapons = filterWeaponsByRequest(filterRequest, allWeapons);
        return filteredWeapons;
    }

    private List<Weapon> filterWeaponsByRequest(FilterRequest filterRequest, List<Weapon> weapons) {
//        List<Weapon> filteredWeapons = weapons.stream()
//                .filter(weapon -> filterRequest.getWeightFilters().contains(weapon.getWeight()))
//                .filter(weapon -> filterRequest.getTierFilters().contains(weapon.getTier()))
//                .collect(Collectors.toList());
//
//        return filteredWeapons;
        return weapons;
    }

}
