package com.mycompany.model;

import com.mycompany.model.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {

    private String id;
    private String name;
    private Double price;
    private String category;

    public ProductDto(Product product) {
        BeanUtils.copyProperties(product, this);
    }
}
