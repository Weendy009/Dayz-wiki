package com.dayz.database.Dotawiki.service.items;

import com.dayz.database.Dotawiki.entity.items.Illnesses;
import com.dayz.database.Dotawiki.entity.items.Item;
import com.dayz.database.Dotawiki.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class IllnessesService {
    private final IllnessesRepository illnessesRepository;
    private final Map<String, IllnessesRepository> repositoryMap;
    private final List<String> itemsNames;

    @Autowired
    public IllnessesService(IllnessesRepository illnessesRepository) {
        this.illnessesRepository = illnessesRepository;

        this.repositoryMap = new HashMap<>();
        repositoryMap.put("illnesses", illnessesRepository);

        itemsNames = new ArrayList<>();
        for (Illnesses illnesses : illnessesRepository.findAll()) {
            itemsNames.add(illnesses.getName());
        }
    }

    public Item getItemByIdAndType(Long itemId, String itemType) {
        Item item = null;
        IllnessesRepository repository = repositoryMap.get(itemType);
        if (repository != null) {
            item = repository.getByIdAndType(itemId, itemType);
        }
        return item;
    }

    public List<Illnesses> getAllIllnesses() {
        return illnessesRepository.findAll();
    }

}
