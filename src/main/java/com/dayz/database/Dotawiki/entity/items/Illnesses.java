package com.dayz.database.Dotawiki.entity.items;

import javax.persistence.*;
import lombok.Data;
import java.util.Objects;

@Data
@Entity
@Table(name = "illnesses")
public class Illnesses implements Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private String name;

    private String causes;

    private String symptoms;

    private String treatment;

    private String type;

    @Override
    public String toString() {
        return "Illnesses{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", causes='" + causes + '\'' +
                ", symptoms='" + symptoms + '\'' +
                ", treatment='" + treatment + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Illnesses illnesses = (Illnesses) o;
        return Objects.equals(id, illnesses.id) && Objects.equals(name, illnesses.name) && Objects.equals(causes, illnesses.causes) && Objects.equals(symptoms, illnesses.symptoms) && Objects.equals(treatment, illnesses.treatment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, causes, symptoms, treatment);
    }

}
