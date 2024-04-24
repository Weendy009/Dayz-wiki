package com.dota.database.Dotawiki.repository;

import com.dota.database.Dotawiki.entity.items.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Long>, ItemRepository {
    public List<Car> getCarsByType(String type);
}
