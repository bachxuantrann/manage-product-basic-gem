package com.gem_intern.manage_product.service;

import com.gem_intern.manage_product.domain.dto.ProductDTO;
import com.gem_intern.manage_product.error.IdInvalidException;

import java.util.List;

public interface ProductService {
    ProductDTO handleCreateProduct(ProductDTO productDTO);

    ProductDTO handleGetProduct(Long id) throws IdInvalidException;

    void handleDeleteProduct(Long id) throws IdInvalidException;

    ProductDTO handleUpdateProduct(ProductDTO productDTO) throws IdInvalidException;

    List<ProductDTO> handleGetAllProducts(String keyword);
}
