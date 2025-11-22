package com.sipndy.product.service.serviceimpl;

import com.sipndy.product.entity.Product;
import com.sipndy.product.repo.ProductRepo;
import com.sipndy.product.service.ProductService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepo productRepo;

    @Override
    public void addProduct(Product product) {
        productRepo.save(product);
    }

    @Override
    public void addProducts(List<Product> products) {
        productRepo.saveAll(products);
    }

    @Override
    public Product getProduct(Long productId) throws Exception {
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


    @Transactional
    @Override
    public boolean deleteAllProduct(String username) throws Exception {
        productRepo.deleteByCreatedBy(username);
        return true;
    }
}
