package com.sipndy.product.rest;

import com.sipndy.product.entity.Product;
import com.sipndy.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/product")
@RestController
public class ProductController {

    private final ProductService productService;

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/add")
    public ResponseEntity addProduct(@RequestBody Product product){
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        product.setCreatedBy(userName);
        productService.addProduct(product);
        return new ResponseEntity<>("Products saved successfully",HttpStatusCode.valueOf(200));
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/addProducts")
    public ResponseEntity addProducts(@RequestBody List<Product> product){

        productService.addProducts(product);

        return new ResponseEntity<>("Products saved successfully",HttpStatusCode.valueOf(200));
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/product")
    public ResponseEntity getProduct(@RequestParam String id){
        try {
           Product product =  productService.getProduct(id);
           return new ResponseEntity<>(product.getProductName(),HttpStatusCode.valueOf(200));
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatusCode.valueOf(500));
        }
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/products")
    public ResponseEntity getProducts(@RequestParam String category){
        try {
            List<String> product =  productService.getProducts(category);
            return new ResponseEntity<>(product,HttpStatusCode.valueOf(200));
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatusCode.valueOf(500));
        }
    }

    @PreAuthorize("hasRole('USER')")
    @DeleteMapping("/deleteProducts")
    public ResponseEntity deleteProducts(){
        try {
            String userName = SecurityContextHolder.getContext().getAuthentication().getName();
            boolean product =  productService.deleteAllProduct(userName);
            return new ResponseEntity<>(product,HttpStatusCode.valueOf(200));
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatusCode.valueOf(500));
        }
    }


}
