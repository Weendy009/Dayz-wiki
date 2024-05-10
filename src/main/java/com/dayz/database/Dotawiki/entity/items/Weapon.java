package com.dayz.database.Dotawiki.entity.items;

import lombok.Data;
import javax.persistence.*;
import java.util.Objects;


@Data
@Entity
@Table(name = "weapons")
public class Weapon implements Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private String name;

    private Double weight;

    private String size;

    private String tier;

    private String type;

    private String caliber;

    private int damage;

    @Override
    public String toString() {
        return "Weapon{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", weight=" + weight +
                ", size='" + size + '\'' +
                ", tier='" + tier + '\'' +
                ", type='" + type + '\'' +
                ", caliber='" + caliber + '\'' +
                ", damage=" + damage +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Weapon weapon = (Weapon) o;
        return damage == weapon.damage && Objects.equals(id, weapon.id) && Objects.equals(name, weapon.name) && Objects.equals(weight, weapon.weight) && Objects.equals(size, weapon.size) && Objects.equals(tier, weapon.tier) && Objects.equals(type, weapon.type) && Objects.equals(caliber, weapon.caliber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, weight, size, tier, type, caliber, damage);
    }
}
