package com.example.productapp.controller;

import com.example.productapp.entity.Product;
import com.example.productapp.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
        Product saved = productService.addProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        if (products.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(products);
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<?> getByCategory(@PathVariable String category) {
        List<Product> products = productService.getByCategory(category);
        if (products.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No products found for category: " + category);
        }
        return ResponseEntity.ok(products);
    }

    @GetMapping("/filter")
    public ResponseEntity<?> getByPriceRange(@RequestParam double min, @RequestParam double max) {
        if (min > max) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Min price must be less than or equal to max price");
        }
        List<Product> products = productService.getByPriceRange(min, max);
        if (products.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No products found in price range: " + min + " - " + max);
        }
        return ResponseEntity.ok(products);
    }

    @GetMapping("/sorted")
    public ResponseEntity<?> getSortedByPrice() {
        List<Product> products = productService.getSortedByPrice();
        if (products.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No products available");
        }
        return ResponseEntity.ok(products);
    }

    @GetMapping("/expensive/{price}")
    public ResponseEntity<?> getExpensiveProducts(@PathVariable double price) {
        List<Product> products = productService.getProductsAbovePrice(price);
        if (products.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No products found above price: " + price);
        }
        return ResponseEntity.ok(products);
    }
}
