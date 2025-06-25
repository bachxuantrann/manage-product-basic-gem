package com.gem_intern.manage_product.controller;

import com.gem_intern.manage_product.domain.Product;
import com.gem_intern.manage_product.error.IdInvalidException;
import com.gem_intern.manage_product.service.ProductService;
import com.gem_intern.manage_product.util.annotation.ApiMessage;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductController {
    private final ProductService productService;

    @Autowired// cái này ko cần cx dc
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    /**
     * @param product
     * @return
     * @apiNote Anh thấy ProductDTO và Product về cấu trúc là giống nhau, nên trả về product cũng đc
     */
    //  create a new product
    @PostMapping("/product")
    @ApiMessage("create a new product")
    public ResponseEntity<?> createProduct(@Valid @RequestBody Product product) {
        return ResponseEntity
                .status(HttpStatus.CREATED).
                body(this.productService.handleCreateProduct(product));
    }

    //  get detail of one product

    /**
     * @param id
     * @return
     * @throws IdInvalidException
     * @apiNote Chỗ này nếu đặt @PathVariable("id") giống với tên biến Long id, thì ta có thể xóa đi như này cho gọn
     */
    @GetMapping("/product/{id}")
    @ApiMessage("get product by id")
    public ResponseEntity<?> getProduct(@PathVariable Long id) throws IdInvalidException {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(this.productService.handleGetProduct(id));
    }

    /**
     * @param id
     * @return
     * @throws IdInvalidException
     * @apiNote Chỗ này nếu đặt @PathVariable("id") giống với tên biến Long id, thì ta có thể xóa đi như này cho gọn
     */
    //  delete a product
    @DeleteMapping("/product/{id}")
    @ApiMessage("delete a product")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) throws IdInvalidException {
        this.productService.handleDeleteProduct(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PutMapping("/product")
    @ApiMessage("update a product")
    public ResponseEntity<?> updateProduct(@RequestBody Product product) throws IdInvalidException {
        return ResponseEntity.status(HttpStatus.OK).body(productService.handleUpdateProduct(product));
    }

    @GetMapping("/product")
    @ApiMessage("get all product")
    public ResponseEntity<List<Product>> getAllProduct(@RequestParam(value = "keyword", required = false) String keyword) {
        return ResponseEntity.status(HttpStatus.OK).body(productService.handleGetAllProduct(keyword));

    }
}
