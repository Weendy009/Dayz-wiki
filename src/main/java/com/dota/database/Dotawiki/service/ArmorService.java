package com.dota.database.Dotawiki.service;

import com.dota.database.Dotawiki.entity.items.Armor;
import com.dota.database.Dotawiki.repository.ArmorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class ArmorService {
    private final ArmorRepository repository;

    @Autowired
    public ArmorService(ArmorRepository repository) {
        this.repository = repository;
    }

    public Set<Armor> getAllShoes() {
        return repository.findByType("shoes");
    }
    public Set<Armor> getAllMasks() {
        return repository.findByType("mask");
    }
    public Set<Armor> getAllHats() {
        return repository.findByType("hats");
    }
    public Set<Armor> getAllGlasses() {
        return repository.findByType("glasses");
    }
    public Set<Armor> getAllGloves() {
        return repository.findByType("gloves");
    }
    public Set<Armor> getAllVests() {
        return repository.findByType("vests");
    }
    public Set<Armor> getAllBackpacks() {
        return repository.findByType("backpacks");
    }
    public Set<Armor> getAllCloth() {
        return repository.findByType("cloth");
    }
    public Set<Armor> getAllBelts() {
        return repository.findByType("belts");
    }

    public Armor getArmorByIdAndType(Long itemId, String itemType) {
        return repository.getArmorByIdAndType(itemId, itemType);
    }

}
