package com.dayz.database.Dotawiki.service.items;

import com.dayz.database.Dotawiki.entity.items.Weapon;
import com.dayz.database.Dotawiki.repository.WeaponRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class WeaponService {
    private final WeaponRepository repository;

    @Autowired
    public WeaponService(WeaponRepository repository) {
        this.repository = repository;
    }

    public Set<Weapon> getAllWeaponsByType(String type) {
        return repository.getAllByType(type);
    }

    public List<Weapon> getAllWeapons() {return repository.findAll();}

}
