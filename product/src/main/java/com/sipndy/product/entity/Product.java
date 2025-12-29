package com.sipndy.product.entity;

import com.sipndy.product.enums.ProductCategory;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
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
    @NotNull
    private String productName;
    @NotNull
    private double productPrice;
    @NotNull
    private ProductCategory productCategory;
    @Min(1)
    @NotNull
    private int productQuantity;
    @NotNull
    private String createdBy;
}
