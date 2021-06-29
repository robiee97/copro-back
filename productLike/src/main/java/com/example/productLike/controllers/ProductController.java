package com.example.productLike.controllers;


import com.example.productLike.dtos.ProductDto;
import com.example.productLike.models.Product;
import com.example.productLike.services.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class ProductController {

    private ProductService productService;

    @PostMapping("/product")
    public ResponseEntity<String> createProduct(@RequestBody ProductDto productDto){
        productService.create(productDto);
        return new ResponseEntity<>("Created", HttpStatus.CREATED);
    }

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProducts(){
        List<Product> list= productService.getAll();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable(name = "id") Long id) {
        Product product = productService.getById(id);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }


    @PutMapping("/product/{id}")
    public ResponseEntity<String> updateProduct(@PathVariable Long id, @Valid @RequestBody ProductDto productDto){
        productService.update(productDto,id);
        return new ResponseEntity<>("Updated",HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/product/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
        productService.delete(id);
        return new ResponseEntity<>("Deleted", HttpStatus.NO_CONTENT);
    }
}
