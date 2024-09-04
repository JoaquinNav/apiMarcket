package com.joaDev.apiMarket.controller;

import com.joaDev.apiMarket.dto.ProductDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/fulltime/api/v1/products")
public class ProductController {

    @GetMapping("/list")
    public ResponseEntity<List<ProductDTO>> getProductList() {
        return null;
    }
}
