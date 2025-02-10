package com.FluffyTerror.Joom2.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {
    @Id//помечаем то что это первичный ключ
    @GeneratedValue(strategy = GenerationType.IDENTITY)//автоматическая генерация столбца id в бд
    private Long id;

    private String firstName;
    private String lastName;
    private String email;
    private String password;


    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL,orphanRemoval = true)
    private Cart cart;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Order> orders;
}
