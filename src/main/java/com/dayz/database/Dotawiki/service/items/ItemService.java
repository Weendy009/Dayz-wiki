package com.dayz.database.Dotawiki.service.items;

import com.dayz.database.Dotawiki.entity.items.*;
import com.dayz.database.Dotawiki.repository.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ItemService {
    private final Map<String, ItemRepository> repositoryMap;
    private final List<String> itemsNames;

    public ItemService(WeaponRepository weaponRepository, ArmorRepository armorRepository, MedicineRepository medicineRepository, CarRepository carRepository) {
        this.repositoryMap = new HashMap<>();
        addRepositoryMap(repositoryMap, weaponRepository, armorRepository, medicineRepository, carRepository);

        itemsNames = new ArrayList<>();
        for (Armor armor : armorRepository.findAll()) {
            itemsNames.add(armor.getName());
        }
        for (Weapon weapon : weaponRepository.findAll()) {
            itemsNames.add(weapon.getName());
        }
        for (Medicine medicine : medicineRepository.findAll()) {
            itemsNames.add(medicine.getName());
        }
        for (Car car : carRepository.findAll()) {
            itemsNames.add(car.getName());
        }
    }

    public Item getItemByIdAndType(Long itemId, String itemType) {
        Item item = null;
        ItemRepository repository = repositoryMap.get(itemType);
        if (repository != null) {
            item = repository.getItemByIdAndType(itemId, itemType);
        }
        return item;
    }

    public List<String> getAllItemsNames() {
        return itemsNames;
    }

    private void addRepositoryMap(Map<String, ItemRepository> repositoryMap, WeaponRepository weaponRepository,
                                  ArmorRepository armorRepository, MedicineRepository medicineRepository, CarRepository carRepository) {
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
        repositoryMap.put("car", carRepository);
    }
}
