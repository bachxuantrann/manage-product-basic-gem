package com.gem_intern.manage_product.service;

import com.gem_intern.manage_product.domain.Product;
import com.gem_intern.manage_product.error.IdInvalidException;
import com.gem_intern.manage_product.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product handleCreateProduct(Product product) {
        return this.productRepository.save(product);
    }

    @Override
    public Product handleGetProduct(Long id) throws IdInvalidException {
        return this.productRepository.findById(id).orElseThrow(() -> new IdInvalidException("id is not found: " + id));
    }

    @Override
    public void handleDeleteProduct(Long id) {
        this.productRepository.deleteById(id);
    }

    @Override
    public Product handleUpdateProduct(Product product) throws IdInvalidException {
        Product currentProduct = this.handleGetProduct(product.getId());
        if (currentProduct != null) {
            currentProduct.setName(product.getName());
            currentProduct.setPrice(product.getPrice());
            currentProduct.setCategory(product.getCategory());
            currentProduct = this.productRepository.save(currentProduct);
        }
        return currentProduct;
    }

    @Override
    public List<Product> handleGetAllProduct(String keyword) {
        return productRepository.findByName(keyword);
    }
}
