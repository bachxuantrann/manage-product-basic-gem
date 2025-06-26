package com.gem_intern.manage_product.service;

import com.gem_intern.manage_product.domain.Product;
import com.gem_intern.manage_product.domain.dto.ProductDTO;
import com.gem_intern.manage_product.error.IdInvalidException;
import com.gem_intern.manage_product.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ProductDTO handleCreateProduct(ProductDTO productDTO) {
        Product product = productDTO.toProduct();
        this.productRepository.save(product);
        return product.toProductDTO();
    }

    @Override
    public ProductDTO handleGetProduct(Long id) throws IdInvalidException {
        return this.productRepository.findById(id).orElseThrow(() -> new IdInvalidException("id is not found: " + id)).toProductDTO();
    }

    @Override
    public void handleDeleteProduct(Long id) throws IdInvalidException {
        Product product =  this.productRepository.findById(id).orElseThrow(() -> new IdInvalidException("id is not found: " + id));
        if(product != null){
            this.productRepository.delete(product);
        }
    }

    @Override
    public ProductDTO handleUpdateProduct(ProductDTO productDTO) throws IdInvalidException {
        Product currentProduct = this.handleGetProduct(productDTO.getId()).toProduct();
        if (currentProduct != null) {
            currentProduct.setName(productDTO.getName());
            currentProduct.setPrice(productDTO.getPrice());
            currentProduct.setCategory(productDTO.getCategory());
            currentProduct = this.productRepository.save(currentProduct);
        }
        return currentProduct.toProductDTO();
    }

    @Override
    public List<ProductDTO> handleGetAllProducts(String keyword) {
        return productRepository.findByName(keyword).stream().map(Product::toProductDTO).collect(Collectors.toList());
    }
}
