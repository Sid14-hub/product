package com.sipndy.product.patterns.factory;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.sipndy.product.entity.Product;
import com.sipndy.product.enums.ProductCategory;
import com.sipndy.product.mapper.ClothingProductMapper;
import com.sipndy.product.mapper.ElectronicProductMapper;
import com.sipndy.product.mapper.GroceryProductMapper;
import com.sipndy.product.patterns.strategy.ProductMapperStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.EnumMap;
import java.util.Map;

@Component
public class ProductFactory {

    private final Map<ProductCategory, ProductMapperStrategy> strategies = new EnumMap<>(ProductCategory.class);

    @Autowired
    public ProductFactory(
            ElectronicProductMapper electronicMapper,
            ClothingProductMapper clothingMapper,
            GroceryProductMapper groceryMapper
    ) {
        strategies.put(ProductCategory.ELECTRONICS, electronicMapper);
        strategies.put(ProductCategory.CLOTHING, clothingMapper);
        strategies.put(ProductCategory.GROCERY, groceryMapper);
    }

    public Product map(ObjectNode node, ProductCategory category) {
        ProductMapperStrategy strategy = strategies.get(category);
        if (strategy == null) {
            throw new IllegalArgumentException("Unsupported category: " + category);
        }
        return strategy.map(node);
    }

}
