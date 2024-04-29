package com.dayz.database.Dotawiki.entity.users;

import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "users_oauth")
public class UserOauth {
    @Id
    private String id;
    private String name;
    private String email;
    private LocalDateTime lastActive;
}
