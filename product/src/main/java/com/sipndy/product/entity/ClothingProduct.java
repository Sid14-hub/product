package com.sipndy.product.entity;

import com.sipndy.product.enums.Gender;
import com.sipndy.product.enums.ProductCategory;
import lombok.Data;

@Data
public class ClothingProduct extends Product{
    public ClothingProduct(){
        super.setProductCategory(ProductCategory.CLOTHING);
    }
    private int size;
    private String color;
    private String fabricType;
    private Gender genderCategory;
}
