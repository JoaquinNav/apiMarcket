package com.joaDev.apiMarket.convert;

import com.joaDev.apiMarket.dto.ProductDTO;
import com.joaDev.apiMarket.model.ProductEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductConverter {

    private final ModelMapper modelMapper;

    @Autowired
    public ProductConverter(ModelMapper modelMapper ) {
        this.modelMapper = modelMapper;
    }

    public ProductDTO entityToDto(ProductEntity product) {
        return  modelMapper.map(product, ProductDTO.class);
    }

    public List<ProductDTO> entitiesToDtos(List<ProductEntity> productEntityList) {
        return productEntityList
                .stream()
                .map(this::entityToDto)
                .toList();
    }

    public ProductEntity dtoToEntity(ProductDTO product) {
        return modelMapper.map(product, ProductEntity.class);
    }
}
