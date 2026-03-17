package com.example.productapp.service;

import com.example.productapp.entity.Product;
import com.example.productapp.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public List<Product> getByCategory(String category) {
        return productRepository.findByCategory(category);
    }

    public List<Product> getByPriceRange(double min, double max) {
        return productRepository.findByPriceBetween(min, max);
    }

    public List<Product> getSortedByPrice() {
        return productRepository.findAllSortedByPrice();
    }

    public List<Product> getProductsAbovePrice(double price) {
        return productRepository.findProductsAbovePrice(price);
    }
}
