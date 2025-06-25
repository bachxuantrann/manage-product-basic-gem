package com.gem_intern.manage_product.service;

import com.gem_intern.manage_product.domain.Product;
import com.gem_intern.manage_product.domain.dto.ProductDTO;
import com.gem_intern.manage_product.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product handleCreateProduct(Product product){
        return this.productRepository.save(product);
    }
    public ProductDTO convertToProductDTO(Product product){
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(product.getId());
        productDTO.setName(product.getName());
        productDTO.setPrice(product.getPrice());
        productDTO.setCategory(product.getCategory());
        return productDTO;
    }
    public Product handleGetProduct(Long id){
        Optional<Product> productOptional = this.productRepository.findById(id);
        if (productOptional.isPresent()){
            return productOptional.get();
        }
        return null;
    }
    public void handleDeleteProduct(Long id){
        this.productRepository.deleteById(id);
    }
    public Product handleUpdateProduct(Product product){
        Product currentProduct = this.handleGetProduct(product.getId());
        if (currentProduct != null){
            currentProduct.setName(product.getName());
            currentProduct.setPrice(product.getPrice());
            currentProduct.setCategory(product.getCategory());
            currentProduct = this.productRepository.save(currentProduct);
        }
        return currentProduct;
    }
    public List<Product> handleGetAllProduct(String keyword){
        if (keyword == null || keyword.isEmpty()){
            return productRepository.findAll();
        }
        return productRepository.findByNameContainingIgnoreCase(keyword);
    }
}
