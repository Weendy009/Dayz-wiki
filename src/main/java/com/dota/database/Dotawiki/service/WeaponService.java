package com.dota.database.Dotawiki.service;

import com.dota.database.Dotawiki.entity.items.Item;
import com.dota.database.Dotawiki.entity.items.Weapon;
import com.dota.database.Dotawiki.repository.WeaponRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class WeaponService {
    private final WeaponRepository repository;
    private final ArmorService armorService;

    @Autowired
    public WeaponService(WeaponRepository repository, ArmorService armorService) {
        this.repository = repository;
        this.armorService = armorService;
    }

    public Set<Weapon> getAllSteelArms() {
        return repository.findByType("steel arms");
    }

    public Set<Weapon> getAllPistols() {
        return repository.findByType("pistol");
    }

    public Set<Weapon> getAllSubmachineGuns() {
        return repository.findByType("submachine guns");
    }

    public Item getWeaponByIdAndType(Long itemId, String itemType) {
        Item item = null;

        switch (itemType) {
            case "steel arms":
                item = repository.getWeaponByIdAndType(itemId, "steel arms");
                break;
            case "pistol":
                item = repository.getWeaponByIdAndType(itemId, "pistol");
                break;
            case "submachine guns":
                item = repository.getWeaponByIdAndType(itemId, "submachine guns");
                break;
            case "shoes":
                item = armorService.getArmorByIdAndType(itemId, "shoes");
                break;
            case "mask":
                item = armorService.getArmorByIdAndType(itemId, "mask");
                break;
            case "hats":
                item = armorService.getArmorByIdAndType(itemId, "hats");
                break;
            case "glasses":
                item = armorService.getArmorByIdAndType(itemId, "glasses");
                break;
            case "gloves":
                item = armorService.getArmorByIdAndType(itemId, "gloves");
                break;
            case "vests":
                item = armorService.getArmorByIdAndType(itemId, "vests");
                break;
            case "backpacks":
                item = armorService.getArmorByIdAndType(itemId, "backpacks");
                break;
            case "cloth":
                item = armorService.getArmorByIdAndType(itemId, "cloth");
                break;
            case "belts":
                item = armorService.getArmorByIdAndType(itemId, "belts");
                break;
        }

        return item;
    }


}
