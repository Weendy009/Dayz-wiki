package com.dota.database.Dotawiki.repository;

import com.dota.database.Dotawiki.entity.items.Item;

public interface ItemRepository{
    Item getItemByIdAndType(Long itemId, String itemType);
}
