package com.example.factureservice.client;

import com.example.factureservice.models.Client;
import com.example.factureservice.models.Product;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.UUID;

@FeignClient("PRODUCT-SERVICE")
public interface ProductRest {

    @GetMapping("/api/products")
    @CircuitBreaker(name = "productService", fallbackMethod = "getAllProductsDefault")
    List<Product> getAllProducts();

    @GetMapping("/api/products/{id}")
    @CircuitBreaker(name = "productService", fallbackMethod = "getProductByIdDefault")
    Product getProductById(@PathVariable UUID id);

    default List<Product> getAllProductsDefault(Exception exception) {
        return List.of();
    }

    default Product getProductByIdDefault(UUID id, Exception exception){
        Product product = new Product();
        product.setId(id);
        product.setDescription("default");
        product.setType("default");
        product.setReference("default");
        product.setPrice(0.0);
        return product;
    }
}
