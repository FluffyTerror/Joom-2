package com.FluffyTerror.Joom2.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String brand;

    private BigDecimal price;

    private int amount;

    private String description;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id")
    private Category category;
    //если продукт удаляют - то и все изображения для него автоматически удаляются
    @OneToMany(mappedBy = "product",cascade = CascadeType.ALL, orphanRemoval = true)

    private List<Image> images;

    public Product(String name, String brand, BigDecimal price, int amount, String description, Category category) {
        this.name = name;
        this.brand = brand;
        this.price = price;
        this.amount = amount;
        this.description = description;
        this.category = category;
    }
}
