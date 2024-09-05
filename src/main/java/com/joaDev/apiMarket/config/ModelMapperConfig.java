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

@Configuration
public class ModelMapperConfig {
    @Bean
    public ModelMapper myModelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        //Mapped ProductEntity to ProductDTO
        modelMapper.addMappings(new PropertyMap<ProductEntity, ProductDTO>() {
            @Override
            protected void configure() {
                map().setIdProduct(source.getIdProduct());
                map().setName(source.getName());
                map().setPrice(source.getPrice());
            }
        });

        //Mapped ProductDTO to ProductEntity
        modelMapper.addMappings(new PropertyMap<ProductDTO, ProductEntity>() {
            @Override
            protected void configure() {
                map().setIdProduct(source.getIdProduct());
                map().setName(source.getName());
                map().setPrice(source.getPrice());
            }
        });

        //Mapped CategoryEntity to CategoryDTO
        modelMapper.addMappings(new PropertyMap<CategoryEntity, CategoryDTO>() {
            @Override
            protected void configure() {
                map().setIdCategory(source.getIdCategory());
                map().setProductList(source.getProductList());
            }
        });

        //Mapped CategoryDTO to CategoryEntity
        modelMapper.addMappings(new PropertyMap<CategoryDTO, CategoryEntity>() {
            @Override
            protected void configure() {
                map().setIdCategory(source.getIdCategory());
                map().setProductList(source.getProductList());
            }
        });

        return modelMapper;
    }
}
