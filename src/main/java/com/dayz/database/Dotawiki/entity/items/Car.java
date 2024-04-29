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
    private Double weight;
    private String capacity;
    private String tier;
    private String type;
    private int fuelTank;
    private int topSpeed;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Car car = (Car) o;

        if (Double.compare(car.weight, weight) != 0) return false;
        if (fuelTank != car.fuelTank) return false;
        if (topSpeed != car.topSpeed) return false;
        if (!Objects.equals(id, car.id)) return false;
        if (!Objects.equals(name, car.name)) return false;
        if (!Objects.equals(capacity, car.capacity)) return false;
        if (!Objects.equals(tier, car.tier)) return false;
        return Objects.equals(type, car.type);
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        temp = Double.doubleToLongBits(weight);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (capacity != null ? capacity.hashCode() : 0);
        result = 31 * result + (tier != null ? tier.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + fuelTank;
        result = 31 * result + topSpeed;
        return result;
    }
}
