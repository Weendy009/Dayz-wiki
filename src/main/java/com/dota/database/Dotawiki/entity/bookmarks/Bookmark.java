package com.dota.database.Dotawiki.entity.bookmarks;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;


@Data
@Entity
@Table(name = "bookmarks")
public class Bookmark {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "item_type")
    private String itemType;

    @Column(name = "item_id")
    private String itemId;
}
