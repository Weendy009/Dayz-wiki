package com.dayz.database.Dotawiki.repository;

import com.dayz.database.Dotawiki.entity.items.Item;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository {
    Item getItemByIdAndType(Long itemId, String itemType);
}
