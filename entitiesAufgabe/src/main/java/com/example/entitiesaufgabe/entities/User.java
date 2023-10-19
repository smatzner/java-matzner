package com.example.entitiesaufgabe.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int userId;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    private int age;

    @OneToMany(mappedBy = "user")
    private List<Purchase> purchases;
}
