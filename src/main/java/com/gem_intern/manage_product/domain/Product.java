package com.gem_intern.manage_product.domain;

import com.gem_intern.manage_product.domain.dto.ProductDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "product")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "name is required")
    private String name;
    @Min(value = 1, message = "price must be > 0")
    private double price;
    private String category;

    public ProductDTO toProductDTO() {
        return new ProductDTO(this.id, this.name, this.price, this.category);
    }
}
