package com.joaDev.apiMarket.service.product;

import com.joaDev.apiMarket.dto.ProductDTO;

import java.math.BigDecimal;
import java.util.List;

public interface IProductService {
    List<ProductDTO> findAllProducts();
    ProductDTO findProductById(Long id);
    List<ProductDTO> finAllProductsBetweenPrice(BigDecimal start, BigDecimal end);
    void createProduct(ProductDTO productDTO);
}
