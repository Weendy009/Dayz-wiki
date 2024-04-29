package com.dayz.database.Dotawiki.service;

import com.dayz.database.Dotawiki.entity.items.Item;
import com.dayz.database.Dotawiki.repository.ArmorRepository;
import com.dayz.database.Dotawiki.repository.ItemRepository;
import com.dayz.database.Dotawiki.repository.MedicineRepository;
import com.dayz.database.Dotawiki.repository.WeaponRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ItemService {
    private final Map<String, ItemRepository> repositoryMap;
    public ItemService(WeaponRepository weaponRepository, ArmorRepository armorRepository, MedicineRepository medicineRepository) {
        this.repositoryMap = new HashMap<>();
        addRepositoryMap(repositoryMap, weaponRepository, armorRepository, medicineRepository);
    }

    public Item getItemByIdAndType(Long itemId, String itemType) {
        Item item = null;
        ItemRepository repository = repositoryMap.get(itemType);
        if (repository != null) {
            item = repository.getItemByIdAndType(itemId, itemType);
        }
        return item;
    }

    private void addRepositoryMap(Map<String, ItemRepository> repositoryMap, WeaponRepository weaponRepository,
                                         ArmorRepository armorRepository, MedicineRepository medicineRepository) {
        repositoryMap.put("steel arms", weaponRepository);
        repositoryMap.put("pistol", weaponRepository);
        repositoryMap.put("submachine guns", weaponRepository);
        repositoryMap.put("shoes", armorRepository);
        repositoryMap.put("mask", armorRepository);
        repositoryMap.put("hats", armorRepository);
        repositoryMap.put("glasses", armorRepository);
        repositoryMap.put("gloves", armorRepository);
        repositoryMap.put("vests", armorRepository);
        repositoryMap.put("backpacks", armorRepository);
        repositoryMap.put("cloth", armorRepository);
        repositoryMap.put("belts", armorRepository);
        repositoryMap.put("Pills", medicineRepository);
        repositoryMap.put("medical consumables", medicineRepository);
        repositoryMap.put("Injections and serums", medicineRepository);
    }
}
