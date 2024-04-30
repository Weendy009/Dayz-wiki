package com.dayz.database.Dotawiki.repository;

import com.dayz.database.Dotawiki.entity.items.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Long>, ItemRepository {
    List<Car> getCarsByType(String type);
}
