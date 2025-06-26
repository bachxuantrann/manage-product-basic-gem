package com.gem_intern.manage_product.controller;

import com.gem_intern.manage_product.domain.dto.ProductDTO;
import com.gem_intern.manage_product.error.IdInvalidException;
import com.gem_intern.manage_product.service.ProductServiceImpl;
import com.gem_intern.manage_product.util.annotation.ApiMessage;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductController {
    private final ProductServiceImpl productService;

    public ProductController(ProductServiceImpl productService) {
        this.productService = productService;
    }

    //  create a new product
    @PostMapping("/product")
    @ApiMessage("create a new product")
    public ResponseEntity<ProductDTO> createProduct(@Valid @RequestBody ProductDTO productDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.productService.handleCreateProduct(productDTO));
    }

    //  get detail of one product
    @GetMapping("/product/{id}")
    @ApiMessage("get product by id")
    public ResponseEntity<ProductDTO> getProduct(@PathVariable("id") Long id) throws IdInvalidException {
        return ResponseEntity.status(HttpStatus.OK).body(this.productService.handleGetProduct(id));
    }

    //  delete a product
    @DeleteMapping("/product/{id}")
    @ApiMessage("delete a product")
    public ResponseEntity<Void> deleteProduct(@PathVariable("id") Long id) throws IdInvalidException {
        this.productService.handleDeleteProduct(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PutMapping("/product")
    @ApiMessage("update a product")
    public ResponseEntity<ProductDTO> updateProduct(@RequestBody ProductDTO productDTO) throws IdInvalidException {
        return ResponseEntity.status(HttpStatus.OK).body(this.productService.handleUpdateProduct(productDTO));
    }

    @GetMapping("/product")
    @ApiMessage("get all product")
    public ResponseEntity<List<ProductDTO>> getAllProduct(@RequestParam(value = "keyword", required = false) String keyword) {
        return ResponseEntity.status(HttpStatus.OK).body(this.productService.handleGetAllProducts(keyword));
    }
}
