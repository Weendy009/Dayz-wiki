package com.dayz.database.Dotawiki.service.items;

import com.dayz.database.Dotawiki.entity.bookmarks.Bookmark;
import com.dayz.database.Dotawiki.repository.*;
import com.dayz.database.Dotawiki.entity.items.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class ItemService {

    private final ItemRepository itemRepository;
    private final ArmorRepository armorRepository;
    private final MedicineRepository medicineRepository;
    private final IllnessesRepository illnessesRepository;
    private final CarRepository carRepository;

    @Autowired
    public ItemService(@Qualifier("weaponRepository") ItemRepository itemRepository, ArmorRepository armorRepository,
                       MedicineRepository medicineRepository, IllnessesRepository illnessesRepository, CarRepository carRepository) {
        this.itemRepository = itemRepository;
        this.armorRepository = armorRepository;
        this.medicineRepository = medicineRepository;
        this.illnessesRepository = illnessesRepository;
        this.carRepository = carRepository;
    }

    public List<Item> getItemsByIdAndType(List<Bookmark> bookmarks) {
        List<Item> resultItems = new ArrayList<>();
        for (Bookmark bookmark : bookmarks) {
            resultItems.add(itemRepository.getItemByIdAndType(bookmark.getItemId(), bookmark.getItemType()));
            resultItems.add(armorRepository.getItemByIdAndType(bookmark.getItemId(), bookmark.getItemType()));
            resultItems.add(medicineRepository.getItemByIdAndType(bookmark.getItemId(), bookmark.getItemType()));
            resultItems.add(illnessesRepository.getItemByIdAndType(bookmark.getItemId(), bookmark.getItemType()));
            resultItems.add(carRepository.getItemByIdAndType(bookmark.getItemId(), bookmark.getItemType()));
        }
        resultItems.removeIf(Objects::isNull);
        return resultItems;
    }

    public List<String> searchByAllNames(String itemName) {
        return itemRepository.searchAllTables(itemName);
    }

}
