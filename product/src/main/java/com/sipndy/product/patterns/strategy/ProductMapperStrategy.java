package com.sipndy.product.patterns.strategy;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.sipndy.product.entity.Product;

public interface ProductMapperStrategy {
    Product map(ObjectNode node);
}
