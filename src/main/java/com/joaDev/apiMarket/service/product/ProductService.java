package com.joaDev.apiMarket.service.product;


import com.joaDev.apiMarket.convert.ProductConverter;
import com.joaDev.apiMarket.dto.ProductDTO;
import com.joaDev.apiMarket.model.ProductEntity;
import com.joaDev.apiMarket.repository.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ProductService implements IProductService{

    private final ProductRepository productRepository;
    //private final ProductConverter productConverter;
    private final ModelMapper modelMapper;
    public ProductService(ProductRepository productRepository,  ModelMapper modelMapper) {
        this.productRepository = productRepository;
        //this.productConverter = productConverter;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<ProductDTO> findAllProducts() {
        List< ProductEntity> productEntityList = productRepository.findAll();
        return productEntityList
                .stream()
                .map(product -> modelMapper.map(product, ProductDTO.class))
                .toList();
        //return productConverter.entitiesToDtos(productEntityList);
    }

    @Override
    public ProductDTO findProductById(Long id) {
        ProductEntity product = this.productRepository
                .findById(id)
                .orElse(null);
        return modelMapper.map(product, ProductDTO.class);
        //return productConverter.entityToDto(product);
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
                .map(product -> modelMapper.map(product, ProductDTO.class))
                .toList();
    }

    @Override
    public ProductDTO createProduct(ProductDTO productDTO) {
        //ProductEntity newProduct = this.productConverter.dtoToEntity(productDTO);
        ProductEntity newProduct = this.modelMapper.map(productDTO, ProductEntity.class);
        //newProduct.setIdProduct(null);
        this.productRepository.save(newProduct);
        return productDTO;
    }

    @Override
    public ProductDTO deleteProductById(Long id) {
        ProductEntity deletedProduct = this.productRepository.findById(id).orElse(null);
        if(deletedProduct == null)
            return null;
        this.productRepository.deleteById(id);
        return this.modelMapper.map(deletedProduct, ProductDTO.class);
        //return this.productConverter.entityToDto(deletedProduct);
    }

    @Override
    public ProductDTO deleteProductByName(String name) {
        ProductEntity deletedProduct = this.productRepository.findByName(name).orElse(null);
        if(deletedProduct == null)
            return null;
        this.productRepository.deleteById(deletedProduct.getIdProduct());
        return this.modelMapper.map(deletedProduct, ProductDTO.class);
        //return this.productConverter.entityToDto(deletedProduct);
    }

    @Override
    public ProductDTO updateProduct(Long id, ProductDTO newProduct) {
        ProductDTO product = this.findProductById(id);
        if (product == null) return null;
        this.createProduct(product);
        return product;
    }


}
