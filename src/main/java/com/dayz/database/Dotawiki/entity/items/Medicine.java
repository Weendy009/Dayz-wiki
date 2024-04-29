package com.dayz.database.Dotawiki.entity.items;

import lombok.Data;

import javax.persistence.*;
import java.util.Objects;

@Data
@Entity
@Table(name = "medicine")
public class Medicine implements Item {

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Medicine medicine = (Medicine) o;

        if (!Objects.equals(id, medicine.id)) return false;
        if (!Objects.equals(name, medicine.name)) return false;
        if (!Objects.equals(weight, medicine.weight)) return false;
        if (!Objects.equals(size, medicine.size)) return false;
        if (!Objects.equals(tier, medicine.tier)) return false;
        return Objects.equals(type, medicine.type);
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
