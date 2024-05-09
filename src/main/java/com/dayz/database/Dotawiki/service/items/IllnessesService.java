package com.dayz.database.Dotawiki.service.items;

import com.dayz.database.Dotawiki.entity.items.Illnesses;
import com.dayz.database.Dotawiki.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IllnessesService {

    private final IllnessesRepository illnessesRepository;

    @Autowired
    public IllnessesService(IllnessesRepository illnessesRepository) {
        this.illnessesRepository = illnessesRepository;
    }

    public List<Illnesses> getAllIllnesses() {
        return illnessesRepository.findAll();
    }

}
