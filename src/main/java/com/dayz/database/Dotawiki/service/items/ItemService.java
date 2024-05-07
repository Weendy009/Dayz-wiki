package com.dayz.database.Dotawiki.service.items;

import com.dayz.database.Dotawiki.repository.ItemRepository;
import com.dayz.database.Dotawiki.entity.items.*;
import com.dayz.database.Dotawiki.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {

    private final ItemRepository itemRepository;

    @Autowired
    public ItemService(@Qualifier("illnessesRepository") ItemRepository itemRepository) {
        this.itemRepository = itemRepository;

    }

    public Item getItemByIdAndType(Long itemId, String itemType) {
        return itemRepository.getItemByIdAndType(itemId, itemType);
    }

    public List<String> searchByAllNames(String itemName) {
        return itemRepository.searchAllTables(itemName);
    }

}
