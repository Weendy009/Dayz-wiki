package com.dayz.database.Dotawiki.service.items;

import com.dayz.database.Dotawiki.entity.items.Armor;
import com.dayz.database.Dotawiki.repository.ArmorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
;
import java.util.Set;

@Service
public class ArmorService {
    private final ArmorRepository repository;

    @Autowired
    public ArmorService(ArmorRepository repository) {
        this.repository = repository;
    }

    public Set<Armor> getAllArmorByType(String typeArmor) {
        return repository.getArmorsByType(typeArmor);
    }

}
