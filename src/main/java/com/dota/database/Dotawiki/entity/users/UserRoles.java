package com.dota.database.Dotawiki.entity.users;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "roles")
public class UserRoles {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "name")
    @Enumerated(EnumType.STRING)
    private Roles name;
}


