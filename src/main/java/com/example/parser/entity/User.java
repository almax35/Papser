package com.example.parser.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import static jakarta.persistence.GenerationType.IDENTITY;

@Data
@Table(name = "users")
@Entity
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private String login;
    private String password;
    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }
}