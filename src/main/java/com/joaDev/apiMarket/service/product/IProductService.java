package com.joaDev.apiMarket.service.product;

import com.joaDev.apiMarket.dto.ProductDTO;

import java.util.List;

public interface IProductService {
    List<ProductDTO> findAllProducts();
    ProductDTO findProductById(Long id);
}
