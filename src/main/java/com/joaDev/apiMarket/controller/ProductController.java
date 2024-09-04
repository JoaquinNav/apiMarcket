package com.joaDev.apiMarket.controller;

import com.joaDev.apiMarket.dto.ProductDTO;
import com.joaDev.apiMarket.service.product.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/fulltime/api/v1/products")
public class ProductController {

    private final IProductService productService;

    @Autowired
    public ProductController(IProductService productService) {
        this.productService = productService;
    }
    @GetMapping("/list")
    public ResponseEntity<List<ProductDTO>> getProductList() {
        return ResponseEntity.status(HttpStatus.OK).body(productService.findAllProducts());
    }
}
