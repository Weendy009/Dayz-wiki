package com.dota.database.Dotawiki.entity.dto;


import lombok.Data;

@Data
public class BookmarkDTO {
    private String name;
    private String size;
    private String weight;
    private String id;
    private String type;

    @Override
    public String toString() {
        return "BookmarkDTO{" +
                "name='" + name + '\'' +
                ", size='" + size + '\'' +
                ", weight='" + weight + '\'' +
                ", id='" + id + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
