package com.joaDev.apiMarket.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "product")
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id", nullable = false, insertable = false, unique = true)
    private Long idProduct;
    @NotNull @NotEmpty
    @Column(nullable = false, length = 60)
    private String name;
    @NotNull
    @Column(nullable = false, precision = 5, scale = 2)
    private BigDecimal price;
}
