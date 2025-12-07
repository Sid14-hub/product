package com.sipndy.product.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "product")
public class Product {
    @Id
    private String productId;
    @NonNull
    private String productName;
    @NonNull
    private double productPrice;
    @NonNull
    private String productCategory;
    @NonNull
    private String createdBy;
}
