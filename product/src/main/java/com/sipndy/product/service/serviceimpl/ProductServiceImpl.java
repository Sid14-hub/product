package com.sipndy.product.service.serviceimpl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.sipndy.product.entity.Cart;
import com.sipndy.product.entity.Product;
import com.sipndy.product.enums.ProductCategory;
import com.sipndy.product.patterns.factory.ProductFactory;
import com.sipndy.product.repo.CartRepo;
import com.sipndy.product.repo.ProductRepo;
import com.sipndy.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {

    private final ProductRepo productRepo;
    private final ProductFactory productFactory;
    private final ObjectMapper objectMapper;
    private final CartRepo cartRepo;

    @Override
    public void addProduct(JsonNode product) {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        ObjectNode node = (ObjectNode) product;
        node.put("createdBy", userName);
        ProductCategory category = ProductCategory.valueOf(node.get("productCategory").asText().toUpperCase());
        productRepo.save(productFactory.map(node, category));
    }

    @Override
    public void addProducts(List<Product> products) {
        productRepo.saveAll(products);
    }

    @Override
    public Product getProduct(String productId) throws Exception {
        Optional<Product> product = productRepo.findById(String.valueOf(productId));
        Supplier<Exception> noProduct = () -> new IllegalArgumentException( "No product with this id is present");
        return product.orElseThrow(noProduct);
    }

    @Override
    public List<String> getProducts(String productCategory) throws Exception{
        Optional<List<Product>> product = productRepo.findByProductCategory(productCategory);
        Supplier<Exception> noProduct = () -> new IllegalArgumentException( "No Such Category");
        List<Product> products = product.orElseThrow(noProduct);
        return products.stream().map(Product::getProductName).collect(Collectors.toList());
    }


    @Override
    @Transactional
    public boolean deleteAllProduct(String username) throws Exception {
        try {
            productRepo.deleteByCreatedBy(username);
            return true;
        } catch (Exception e){
            log.info(e.getMessage());
        }
        return false;
    }

    @Override
    public void addToCart(JsonNode product) {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        ObjectNode node = (ObjectNode) product;
        node.put("addedBy", userName);
        Cart cart = objectMapper.convertValue(node, Cart.class);
        cartRepo.save(cart);
    }

    @Override
    public List<JsonNode> findAllCart() {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<List<Cart>> cartValues = Optional.of(cartRepo.findByAddedBy(userName));
        return cartValues.get().stream().map(x->objectMapper.convertValue(x,JsonNode.class)).toList();
    }
}
