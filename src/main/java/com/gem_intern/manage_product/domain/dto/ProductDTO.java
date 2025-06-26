package com.gem_intern.manage_product.domain.dto;

import com.gem_intern.manage_product.domain.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {
    private Long id;
    private String name;
    private double price;
    private String category;

    public Product toProduct() {
        return new Product(this.id, this.name, this.price, this.category);
    }
}
