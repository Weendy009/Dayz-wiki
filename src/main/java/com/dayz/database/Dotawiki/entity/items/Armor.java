package com.dayz.database.Dotawiki.entity.items;
import lombok.Data;
import javax.persistence.*;
import java.util.Objects;


@Data
@Entity
@Table(name = "armors")
public class Armor implements Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private String name;

    private String type;

    private int durability;

    private Double weight;

    private String size;

    private String insulation;

    @Override
    public String toString() {
        return "Armor{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", durability=" + durability +
                ", weight=" + weight +
                ", size='" + size + '\'' +
                ", type='" + type + '\'' +
                ", insulation='" + insulation + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Armor armor = (Armor) o;

        if (durability != armor.durability) return false;
        if (!Objects.equals(id, armor.id)) return false;
        if (!Objects.equals(name, armor.name)) return false;
        if (!Objects.equals(weight, armor.weight)) return false;
        if (!Objects.equals(size, armor.size)) return false;
        if (!Objects.equals(type, armor.type)) return false;
        return Objects.equals(insulation, armor.insulation);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + durability;
        result = 31 * result + (weight != null ? weight.hashCode() : 0);
        result = 31 * result + (size != null ? size.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (insulation != null ? insulation.hashCode() : 0);
        return result;
    }
}
