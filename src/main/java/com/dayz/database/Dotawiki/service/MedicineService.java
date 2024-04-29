package com.dayz.database.Dotawiki.service;

import com.dayz.database.Dotawiki.entity.items.Medicine;
import com.dayz.database.Dotawiki.repository.MedicineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicineService {
    private final MedicineRepository medicineRepository;

    @Autowired
    public MedicineService(MedicineRepository medicineRepository) {
        this.medicineRepository = medicineRepository;
    }

    public List<Medicine> getAllMedicamentByType(String typeMedicament) {
        return medicineRepository.getMedicinesByType(typeMedicament);
    }

}
