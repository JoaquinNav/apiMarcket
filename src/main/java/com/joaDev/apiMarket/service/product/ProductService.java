package com.joaDev.apiMarket.service.product;


import com.joaDev.apiMarket.convert.ProductConverter;
import com.joaDev.apiMarket.dto.ProductDTO;
import com.joaDev.apiMarket.model.ProductEntity;
import com.joaDev.apiMarket.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
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

    @Override
    public List<ProductDTO> finAllProductsBetweenPrice(BigDecimal start, BigDecimal end) {
        return this.findAllProducts()
                .stream()
                .filter(product -> product.getPrice().compareTo(start) >= 0 && product.getPrice().compareTo(end) <= 0)
                .toList();
    }

    @Override
    public List<ProductDTO> findAllProductsByName(String name) {
        List<ProductEntity> productEntityList = this.productRepository.findAll();
        return productEntityList
                .stream()
                .filter(product -> product.getName().toLowerCase().contains(name.toLowerCase()))
                .map(productConverter::entityToDto)
                .toList();
    }

    @Override
    public void createProduct(ProductDTO productDTO) {
        ProductEntity newProduct = this.productConverter.dtoToEntity(productDTO);
        newProduct.setIdProduct(null);
        this.productRepository.save(newProduct);
    }

    @Override
    public ProductDTO deleteProductById(Long id) {
        ProductEntity deletedProduct = this.productRepository.findById(id).orElse(null);
        if(deletedProduct == null)
            return null;
        this.productRepository.deleteById(id);
        return this.productConverter.entityToDto(deletedProduct);
    }

    @Override
    public ProductDTO deleteProductByName(String name) {
        ProductEntity deletedProduct = this.productRepository.findByName(name).orElse(null);
        if(deletedProduct == null)
            return null;
        this.productRepository.deleteById(deletedProduct.getIdProduct());
        return this.productConverter.entityToDto(deletedProduct);
    }

    @Override
    public ProductDTO updateProduct(Long id, ProductDTO newProduct) {
        ProductDTO product = this.findProductById(id);
        if (product == null) return null;
        this.createProduct(product);
        return product;
    }


}
