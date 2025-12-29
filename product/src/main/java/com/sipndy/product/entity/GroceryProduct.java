package com.sipndy.product.entity;

import com.sipndy.product.enums.ProductCategory;
import jakarta.validation.constraints.Future;
import jdk.jfr.BooleanFlag;
import lombok.Data;

import java.util.Date;

@Data
public class GroceryProduct  extends Product{
    public GroceryProduct(){
        super.setProductCategory(ProductCategory.GROCERY);
    }
    @Future
    private Date expirationDate;
    private String weight;
    @BooleanFlag
    private boolean organic;
    private String storageInstructions;
}
