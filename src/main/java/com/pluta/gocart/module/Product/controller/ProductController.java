package com.pluta.gocart.module.Product.controller;

import com.pluta.gocart.module.Product.dao.ProductRepository;
import com.pluta.gocart.module.Product.model.Product;
import com.pluta.gocart.module.Product.payload.request.UpdateProductRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Objects;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*",maxAge = 3600)
@RequestMapping(path = "product")
public class ProductController {
    @Autowired
    private ProductRepository productRepository;
    @PostMapping("/create")
    public ResponseEntity<?> createProduct(@Valid @RequestBody Product product) {
        productRepository.save(product);
        return ResponseEntity.ok().body("Product Added");
    }
    @GetMapping("/get")
    public ResponseEntity<?> getProduct(@RequestParam Long id) {
        Optional<Product> possibleProduct = productRepository.findById(id);
        if (!possibleProduct.isPresent()) return ResponseEntity.badRequest().body("Bad id");
        return ResponseEntity.ok().body(possibleProduct.get());
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateProduct(@RequestBody UpdateProductRequest request) {
        Optional<Product> optionalProduct = productRepository.findByName(request.getName());
        if(optionalProduct.isEmpty()) return ResponseEntity.badRequest().body("Bad product name");
        Product product = optionalProduct.get();
        if(!Objects.isNull(request.getNewName())) product.setName(request.getName());
        if(!Objects.isNull(request.getNewPrice())) product.setPrice(request.getNewPrice());
        if(!Objects.isNull(request.getUnit())) product.setUnit(request.getUnit());

        productRepository.save(product);

        return ResponseEntity.ok().body("Updated Successfully@");
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteProduct(@RequestParam Long id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if(optionalProduct.isEmpty()) return ResponseEntity.badRequest().body("Bad product id");
        productRepository.delete(optionalProduct.get());
        return ResponseEntity.ok().body("Product deleted successfully!");
    }
}
