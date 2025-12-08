package com.sipndy.product.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.sipndy.product.entity.ElectronicProduct;
import com.sipndy.product.entity.Product;
import com.sipndy.product.patterns.strategy.ProductMapperStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ElectronicProductMapper implements ProductMapperStrategy {
    private final ObjectMapper objectMapper;

    @Override
    public Product map(ObjectNode node) {
        return objectMapper.convertValue(node, ElectronicProduct.class);
    }
}
