package com.sipndy.product.entity;

import com.sipndy.product.enums.ProductCategory;
import lombok.Data;

import java.util.Date;

@Data
public class GroceryProduct  extends Product{
    public GroceryProduct(){
        super.setProductCategory(ProductCategory.GROCERY);
    }
    private Date expirationDate;
    private String weight;
    private boolean organic;
    private String storageInstructions;
}
