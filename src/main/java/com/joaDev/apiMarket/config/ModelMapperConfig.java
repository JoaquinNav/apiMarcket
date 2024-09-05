package com.joaDev.apiMarket.config;

import com.joaDev.apiMarket.dto.CategoryDTO;
import com.joaDev.apiMarket.dto.ProductDTO;
import com.joaDev.apiMarket.model.CategoryEntity;
import com.joaDev.apiMarket.model.ProductEntity;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class ModelMapperConfig {
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        modelMapper.typeMap(ProductEntity.class, ProductDTO.class).addMappings(mapper -> {
            mapper.map(src -> src.getCategory().getIdCategory(), ProductDTO::setIdCategory);
            mapper.map(src -> src.getCategory().getDescription(), ProductDTO::setDescription);
        });
        modelMapper.typeMap(ProductDTO.class, ProductEntity.class);
        modelMapper.typeMap(CategoryEntity.class, CategoryDTO.class);
        modelMapper.typeMap(CategoryDTO.class, CategoryEntity.class);
        return modelMapper;
    }
}
