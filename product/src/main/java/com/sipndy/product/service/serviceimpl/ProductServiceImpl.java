package com.sipndy.product.service.serviceimpl;

import com.sipndy.product.entity.Product;
import com.sipndy.product.repo.ProductRepo;
import com.sipndy.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

    @Override
    public void addProduct(Product product) {
        try {
            log.info("Saving a product with id {}", product.getProductId());
            productRepo.save(product);
            log.info("Saved product");
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
}
