package com.dayz.database.Dotawiki.entity.items;

import lombok.Data;

import javax.persistence.*;
import java.util.Objects;

@Table(name = "cars")
@Entity
@Data
public class Car implements Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    private String name;
    private String capacity;
    private String type;
    private int fuelTank;
    private int topSpeed;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return fuelTank == car.fuelTank && topSpeed == car.topSpeed && Objects.equals(id, car.id) && Objects.equals(name, car.name) && Objects.equals(capacity, car.capacity) && Objects.equals(type, car.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, capacity, type, fuelTank, topSpeed);
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public String getType() {
        return type;
    }
}
