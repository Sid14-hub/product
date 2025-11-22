package com.sipndy.product.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;
    @NonNull
    private String productName;
    @NonNull
    private double productPrice;
    @NonNull
    private String productCategory;
    @NonNull
    @Column(name = "created_by")
    private String createdBy;
}
