package com.dota.database.Dotawiki.entity.items;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter


@Data
@Entity
@Table(name = "armors")
public class Armor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private String name;

    private int durability;

    private Double weight;

    private String size;

    private String tier;

    private String type;

    private String insulation;

    @Override
    public String toString() {
        return "Armor{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", durability=" + durability +
                ", weight=" + weight +
                ", size='" + size + '\'' +
                ", tier='" + tier + '\'' +
                ", type='" + type + '\'' +
                ", insulation='" + insulation + '\'' +
                '}';
    }
}
