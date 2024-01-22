package project.rabbitmq.producer.controllers;

import dtos.ProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.rabbitmq.producer.services.ProductService;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService service;

    @PostMapping
    public ResponseEntity<ProductDTO> producesMessage(@RequestBody ProductDTO dto){
        service.createProduct(dto);

        return ResponseEntity.status(CREATED).build();
    }
}
