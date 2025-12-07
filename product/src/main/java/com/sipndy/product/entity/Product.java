package com.sipndy.product.entity;

import com.sipndy.product.enums.ProductCategory;
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
    private ProductCategory productCategory;
    @NonNull
    private String createdBy;
}
