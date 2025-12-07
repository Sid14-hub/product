package com.sipndy.product.entity;

import com.sipndy.product.enums.ProductCategory;
import lombok.Data;

@Data
public class ElectronicProduct extends Product{

    public ElectronicProduct(){
        super.setProductCategory(ProductCategory.ELECTRONICS);
    }
    private String brand;
    private String model;
    private int warrantyPeriod;
    private int powerConsumption;
}
