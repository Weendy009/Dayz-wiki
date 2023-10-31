package com.dota.database.Dotawiki.service;

import com.dota.database.Dotawiki.entity.items.Armor;
import com.dota.database.Dotawiki.entity.items.Weapon;
import com.dota.database.Dotawiki.repository.ArmorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArmorService {
    private final ArmorRepository repository;

    @Autowired
    public ArmorService(ArmorRepository repository) {
        this.repository = repository;
    }

    public List<Armor> getAllShoes() {
        return repository.findByType("shoes");
    }
    public List<Armor> getAllMasks() {
        return repository.findByType("mask");
    }
    public List<Armor> getAllHats() {
        return repository.findByType("hats");
    }
    public List<Armor> getAllGlasses() {
        return repository.findByType("glasses");
    }
    public List<Armor> getAllGloves() {
        return repository.findByType("gloves");
    }
    public List<Armor> getAllVests() {
        return repository.findByType("vests");
    }
    public List<Armor> getAllBackpacks() {
        return repository.findByType("backpacks");
    }
    public List<Armor> getAllCloth() {
        return repository.findByType("cloth");
    }
    public List<Armor> getAllBelts() {
        return repository.findByType("belts");
    }

}
