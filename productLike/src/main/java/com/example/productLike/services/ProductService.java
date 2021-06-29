package com.example.productLike.services;

import com.example.productLike.dtos.ProductDto;
import com.example.productLike.exceptions.ProductAppException;
import com.example.productLike.models.Product;
import com.example.productLike.repo.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Transactional
public class ProductService {

    private ProductRepository productRepository;

    public void create(ProductDto productDto){
        Product product= new Product();
        product.setId(productDto.getId());
        product.setTitle(productDto.getTitle());
        product.setImage(productDto.getImage());
        product.setLikes(productDto.getLikes());

        productRepository.save(product);
    }

    public List<Product> getAll(){
        return productRepository.findAll();
    }
    public Product getById(Long id){
        return productRepository.findById(id).
                orElseThrow(()-> new ProductAppException("Product Not Found!!!"));
    }
    public void update(ProductDto productDto,Long id){
        Product product= productRepository.findById(id).
                orElseThrow(()-> new ProductAppException("Product Not Found!!!"));

        product.setId(productDto.getId());
        product.setTitle(productDto.getTitle());
        product.setImage(productDto.getImage());
        product.setLikes(productDto.getLikes());

        productRepository.save(product);
    }
    public void delete(Long id){
        Product product= productRepository.findById(id).
                orElseThrow(()-> new ProductAppException("Product Not Found!!!"));
        productRepository.delete(product);
    }
}
