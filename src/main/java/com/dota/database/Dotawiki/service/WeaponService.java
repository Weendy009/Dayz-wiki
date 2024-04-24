package com.dota.database.Dotawiki.service;

import com.dota.database.Dotawiki.entity.items.Weapon;
import com.dota.database.Dotawiki.repository.WeaponRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class WeaponService {
    private final WeaponRepository repository;

    @Autowired
    public WeaponService(WeaponRepository repository) {
        this.repository = repository;
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

}
