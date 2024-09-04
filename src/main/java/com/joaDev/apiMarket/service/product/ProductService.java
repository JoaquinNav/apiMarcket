package com.joaDev.apiMarket.service.product;


import com.joaDev.apiMarket.convert.ProductConverter;
import com.joaDev.apiMarket.dto.ProductDTO;
import com.joaDev.apiMarket.model.ProductEntity;
import com.joaDev.apiMarket.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService implements IProductService{

    private final ProductRepository productRepository;
    private final ProductConverter productConverter;
    public ProductService(ProductRepository productRepository, ProductConverter productConverter) {
        this.productRepository = productRepository;
        this.productConverter = productConverter;
    }

    @Override
    public List<ProductDTO> findAllProducts() {
        List< ProductEntity> productEntityList = productRepository.findAll();
        return productConverter.entitiesToDtos(productEntityList);
    }

    @Override
    public ProductDTO findProductById(Long id) {
        ProductEntity product = this.productRepository
                .findById(id)
                .orElse(null);
        return productConverter.entityToDto(product);
    }
}
