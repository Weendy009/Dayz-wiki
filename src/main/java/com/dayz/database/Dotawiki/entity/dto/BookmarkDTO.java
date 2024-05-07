package com.dayz.database.Dotawiki.entity.dto;


import lombok.Data;

@Data
public class BookmarkDTO {
    private String id;
    private String name;
    private String type;

    @Override
    public String toString() {
        return "BookmarkDTO{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
