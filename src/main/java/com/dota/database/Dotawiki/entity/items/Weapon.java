package com.dota.database.Dotawiki.entity.items;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter


@Data
@Entity
@Table(name = "weapons")
public class Weapon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private String name;

    private Double weight;

    private String size;

    private String tier;

    private String type;

    @Override
    public String toString() {
        return "Weapon{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", weight=" + weight +
                ", size='" + size + '\'' +
                ", tier='" + tier + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
