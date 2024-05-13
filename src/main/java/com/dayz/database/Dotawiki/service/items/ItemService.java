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

    @Autowired
    public ItemService(@Qualifier("weaponRepository") ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public List<Item> getItemsByIdAndType(List<Bookmark> bookmarks) {
        List<Item> resultItems = new ArrayList<>();
        for (Bookmark bookmark : bookmarks) {
            resultItems.add(itemRepository.getIllnessesByIdAndType(bookmark.getItemId(), bookmark.getItemType()));
            resultItems.add(itemRepository.getArmorByIdAndType(bookmark.getItemId(), bookmark.getItemType()));
            resultItems.add(itemRepository.getCarByIdAndType(bookmark.getItemId(), bookmark.getItemType()));
            resultItems.add(itemRepository.getWeaponByIdAndType(bookmark.getItemId(), bookmark.getItemType()));
            resultItems.add(itemRepository.getMedicineByIdAndType(bookmark.getItemId(), bookmark.getItemType()));
            resultItems.add(itemRepository.getFoodByIdAndType(bookmark.getItemId(), bookmark.getItemType()));
        }
        resultItems.removeIf(Objects::isNull);
        return resultItems;
    }

    public List<String> searchByAllNames(String itemName) {
        return itemRepository.searchAllTables(itemName);
    }

}
