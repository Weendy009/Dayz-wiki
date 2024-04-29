package com.dayz.database.Dotawiki.controller;

import com.dayz.database.Dotawiki.entity.dto.FilterRequest;
import com.dayz.database.Dotawiki.entity.items.Armor;
import com.dayz.database.Dotawiki.entity.items.Item;
import com.dayz.database.Dotawiki.entity.items.Weapon;
import com.dayz.database.Dotawiki.service.ArmorService;
import com.dayz.database.Dotawiki.service.WeaponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class ItemsController {
    private final WeaponService weaponService;
    private final ArmorService armorService;

    @Autowired
    public ItemsController(WeaponService weaponService, ArmorService armorService) {
        this.weaponService = weaponService;
        this.armorService = armorService;
    }

    @PostMapping("/weapons/filter")
    public Set<Item> counterWeaponList (@RequestBody FilterRequest filterRequest) {
        Set<Weapon> allWeapons = new HashSet<>();
        switch (filterRequest.getItemCategory()) {
            case 0:
                allWeapons = weaponService.getAllSteelArms();
                break;
            case 1:
                allWeapons = weaponService.getAllPistols();
                break;
            case 2:
                allWeapons = weaponService.getAllSubmachineGuns();
                break;
        }

        return filterItemsByRequest(filterRequest.getSelectedFilters(), new HashSet<>(allWeapons));
    }
    @PostMapping("/equipment/filter")
    public Set<Item> counterArmorList (@RequestBody FilterRequest filterRequest) {
        Set<Armor> allArmors = new HashSet<>();
        switch (filterRequest.getItemCategory()) {
            case 0:
                allArmors = armorService.getAllShoes();
                break;
            case 1:
                allArmors = armorService.getAllMasks();
                break;
            case 2:
                allArmors = armorService.getAllHats();
                break;
            case 3:
                allArmors = armorService.getAllGlasses();
                break;
            case 4:
                allArmors = armorService.getAllGloves();
                break;
            case 5:
                allArmors = armorService.getAllVests();
                break;
            case 6:
                allArmors = armorService.getAllBackpacks();
                break;
            case 7:
                allArmors = armorService.getAllCloth();
                break;
            case 8:
                allArmors = armorService.getAllBelts();
                break;
        }

        return filterItemsByRequest(filterRequest.getSelectedFilters(), new HashSet<>(allArmors));
    }

    private Set<Item> filterItemsByRequest(Set<String> filters, Set<Item> items) {
        Set<String> validFilters = new HashSet<>(Arrays.asList("Tier None", "Tier 1", "Tier 2", "Tier 3", "Tier 4",
                "0kg < 2kg", "2kg < 5kg", "5kg < 10kg", "10kg < ∞kg"));

        return items.stream()
                .filter(item -> filters.stream()
                        .map(String::trim)
                        .filter(validFilters::contains)
                        .allMatch(filter -> {
                            String[] parts = filter.split(" ");
                            switch (parts[0]) {
                                case "Tier":
                                    return item.getTier().contains(parts[1]);
                                case "0kg":
                                    return item.getWeight() >= 0.0 && item.getWeight() < 2.0;
                                case "2kg":
                                    return item.getWeight() >= 2.0 && item.getWeight() < 5.0;
                                case "5kg":
                                    return item.getWeight() >= 5.0 && item.getWeight() < 10.0;
                                case "10kg":
                                    return item.getWeight() >= 10.0;
                                default:
                                    return false;
                            }
                        }))
                .collect(Collectors.toSet());
    }

}
