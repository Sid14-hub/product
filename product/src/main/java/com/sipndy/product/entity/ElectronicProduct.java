package com.sipndy.product.entity;

import com.sipndy.product.enums.ProductCategory;
import lombok.Data;
import lombok.NonNull;

@Data
public class ElectronicProduct extends Product{

    public ElectronicProduct(){
        super.setProductCategory(ProductCategory.ELECTRONICS);
    }
    @NonNull
    private String brand;
    private String model;
    private int warrantyPeriod;
    private int powerConsumption;
}
