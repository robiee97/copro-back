package com.example.productLike.consumers;

import com.example.productLike.dtos.ProductDto;
import com.example.productLike.repo.ProductRepository;
import com.example.productLike.services.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Component
@AllArgsConstructor
public class ProductConsumer {

    private ProductService productService;
    private ProductRepository productRepository;

    @KafkaListener(topics = {"product_created", "product_updated"},groupId = "myId",containerFactory ="productKafkaListenerContainerFactory")
    public void listen(ProductDto productDto){
        if(productRepository.existsById(productDto.getId())){
            productService.update(productDto,productDto.getId());
        }else{
            productService.create(productDto);
        }
    }
    @KafkaListener(topics = {"product_deleted"},groupId = "myId",containerFactory = "kafkaListenerContainerFactory")
    public void listen(String data){
        Long id=Long.parseLong(data.substring(1,data.length()-1));
        if(productRepository.existsById(id)){
            productService.delete(id);
        }else{
            System.out.println("Product Not found");
        }
    }

}
