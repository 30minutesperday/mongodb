package com.mycompany.controller;

import com.mycompany.model.ProductDto;
import com.mycompany.model.entity.Product;
import com.mycompany.repository.ProductRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class ProductController {

    private final ProductRepository repository;
    public ProductController(ProductRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    public String createProduct(@RequestBody ProductDto productDto) {
        Product product = new Product();
        BeanUtils.copyProperties(productDto, product);
        repository.save(product);

        return product.getId();
    }

    @GetMapping
    public List<ProductDto> getProducts() {
        return repository.findAll().stream()
                .map(ProductDto::new).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ProductDto getProduct(@PathVariable String id) {
        Optional<Product> product = repository.findById(id);
        if (product.isPresent()) {
            ProductDto productDto = new ProductDto();
            BeanUtils.copyProperties(product.get(), productDto);
            return productDto;
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable String id) {
        repository.deleteById(id);
    }

    @GetMapping("/categories/{category}")
    public List<ProductDto> getCategories(@PathVariable String category) {
        return repository.findByCategory(category).stream()
                .map(ProductDto::new).collect(Collectors.toList());
    }
}
