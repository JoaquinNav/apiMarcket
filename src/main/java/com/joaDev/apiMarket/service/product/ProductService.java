package com.joaDev.apiMarket.service.product;


import com.joaDev.apiMarket.dto.ProductDTO;
import com.joaDev.apiMarket.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService implements IProductService{

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<ProductDTO> findAllProducts() {
        return null;
    }
}
