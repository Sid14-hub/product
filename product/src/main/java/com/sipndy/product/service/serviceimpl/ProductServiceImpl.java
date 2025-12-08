package com.sipndy.product.service.serviceimpl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.sipndy.product.entity.ClothingProduct;
import com.sipndy.product.entity.ElectronicProduct;
import com.sipndy.product.entity.GroceryProduct;
import com.sipndy.product.entity.Product;
import com.sipndy.product.enums.ProductCategory;
import com.sipndy.product.repo.ProductRepo;
import com.sipndy.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {

    private final ProductRepo productRepo;
    private final ObjectMapper objectMapper;

    @Override
    public void addProduct(JsonNode product) {
        try {
            Product productObj = mapProducts(product);
            if(productObj!=null) {
                productRepo.save(productObj);
                log.info("Saved product");
            }else {
                log.info("Nothing Saved");
            }
        } catch (Exception e){
            log.info(e.getMessage());
        }
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

    private Product mapProducts(JsonNode product){
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        ObjectNode node = (ObjectNode) product;
        node.put("createdBy", userName);
        ProductCategory pc = ProductCategory.valueOf(node.get("productCategory").asText().toUpperCase());
        Product prod;
        switch (pc) {
            case ELECTRONICS:
                prod = objectMapper.convertValue(node, ElectronicProduct.class);
                break;
            case CLOTHING:
                prod = objectMapper.convertValue(node, ClothingProduct.class);
                break;
            case GROCERY:
                prod = objectMapper.convertValue(node, GroceryProduct.class);
                break;
            default:
                throw new IllegalArgumentException("Unsupported product category: " + pc);
        }
        return prod;
    }
}
