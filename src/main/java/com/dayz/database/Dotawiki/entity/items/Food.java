package com.dayz.database.Dotawiki.entity.items;

import lombok.Data;

import javax.persistence.*;
import java.util.Objects;

@Table(name = "foods")
@Entity
@Data
public class Food implements Item{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private String name;

    private int calories;

    private int thirst;

    private String type;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Food food = (Food) o;
        return calories == food.calories && thirst == food.thirst && Objects.equals(id, food.id) && Objects.equals(name, food.name) && Objects.equals(type, food.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, calories, thirst, type);
    }
}
