package com.joaDev.apiMarket.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "product")
@Getter @Setter
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id", nullable = false, insertable = false, unique = true)
    private Long idProduct;
    @Column(nullable = false, length = 60)
    private String name;
    @Column(nullable = false, precision = 5, scale = 2)
    private BigDecimal price;
}
