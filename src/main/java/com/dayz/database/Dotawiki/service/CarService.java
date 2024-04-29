package com.dayz.database.Dotawiki.service;

import com.dayz.database.Dotawiki.entity.items.Car;
import com.dayz.database.Dotawiki.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService {

    private final CarRepository carRepository;

    @Autowired
    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public List<Car> getAllCarsByType(String typeCar) {
        return carRepository.getCarsByType(typeCar);
    }

}
