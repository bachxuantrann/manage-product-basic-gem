package com.gem_intern.manage_product.controller;

import com.gem_intern.manage_product.domain.Product;
import com.gem_intern.manage_product.domain.dto.ProductDTO;
import com.gem_intern.manage_product.error.IdInvalidException;
import com.gem_intern.manage_product.service.ProductService;
import com.gem_intern.manage_product.util.annotation.ApiMessage;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class ProductController {
    private final ProductService productService;
    public ProductController(ProductService productService) {
        this.productService = productService;
    }
//  create a new product
    @PostMapping("/product")
    @ApiMessage("create a new product")
    public ResponseEntity<ProductDTO> createProduct (@Valid @RequestBody Product product){
        Product newProduct = this.productService.handleCreateProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(this.productService.convertToProductDTO(newProduct));
    }
//  get detail of one product
    @GetMapping("/product/{id}")
    @ApiMessage("get product by id")
    public ResponseEntity<ProductDTO> getProduct(@PathVariable("id") Long id) throws IdInvalidException {
        Product product = this.productService.handleGetProduct(id);
//        if product is null
        if (product == null){
            throw new IdInvalidException("id is not found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(this.productService.convertToProductDTO(product));
    }
//  delete a product
    @DeleteMapping("/product/{id}")
    @ApiMessage("delete a product")
    public ResponseEntity<Void> deleteProduct(@PathVariable("id") Long id ) throws IdInvalidException {
        Product currentProduct = this.productService.handleGetProduct(id);
//   if product is null
        if (currentProduct == null){
            throw new IdInvalidException("id is not found");
        }
        this.productService.handleDeleteProduct(id);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
    @PutMapping("/product")
    @ApiMessage("update a product")
    public ResponseEntity<ProductDTO> updateProduct(@RequestBody Product product) throws IdInvalidException{
        Product productUpdated = this.productService.handleUpdateProduct(product);
//      if product need updated null
        if (productUpdated == null){
            throw new IdInvalidException("id is not found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(this.productService.convertToProductDTO(productUpdated));
    }
    @GetMapping("/product")
    @ApiMessage("get all product")
    public ResponseEntity<List<ProductDTO>> getAllProduct(@RequestParam(value = "keyword",required = false) String keyword){
        List<Product> products = this.productService.handleGetAllProduct(keyword);
        List<ProductDTO> productDTOs = products.stream().map(productService::convertToProductDTO).collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(productDTOs);

    }
}
