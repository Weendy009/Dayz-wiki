package com.dota.database.Dotawiki.entity.items;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Weapon weapon = (Weapon) o;

        if (!Objects.equals(id, weapon.id)) return false;
        if (!Objects.equals(name, weapon.name)) return false;
        if (!Objects.equals(weight, weapon.weight)) return false;
        if (!Objects.equals(size, weapon.size)) return false;
        if (!Objects.equals(tier, weapon.tier)) return false;
        return Objects.equals(type, weapon.type);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (weight != null ? weight.hashCode() : 0);
        result = 31 * result + (size != null ? size.hashCode() : 0);
        result = 31 * result + (tier != null ? tier.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        return result;
    }
}
