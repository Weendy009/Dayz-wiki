package com.dota.database.Dotawiki.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "users_info")
public class UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "enabled")
    private boolean enabled;

    @Column(name = "last_active")
    private Date lastActive;

    @Column(name = "activated_email")
    private boolean activatedEmail;

}
