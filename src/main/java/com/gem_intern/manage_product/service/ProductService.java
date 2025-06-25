package com.gem_intern.manage_product.service;

import com.gem_intern.manage_product.domain.Product;
import com.gem_intern.manage_product.error.IdInvalidException;

import java.util.List;

public interface ProductService {
    Product handleCreateProduct(Product product);

    Product handleGetProduct(Long id) throws IdInvalidException;

    void handleDeleteProduct(Long id);

    Product handleUpdateProduct(Product product) throws IdInvalidException;

    List<Product> handleGetAllProduct(String keyword);

}
