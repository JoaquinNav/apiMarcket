package com.joaDev.apiMarket.convert;


import com.joaDev.apiMarket.dto.CategoryDTO;
import com.joaDev.apiMarket.model.CategoryEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CategoryConverter {

    private final ModelMapper modelMapper;
    private final ProductConverter productMapper;

    @Autowired
    public CategoryConverter(ModelMapper modelMapper, ProductConverter productMapper) {
        this.modelMapper = modelMapper;
        this.productMapper = productMapper;
    }

    public CategoryDTO entityToDto(CategoryEntity categoryEntity) {
        return modelMapper.map(categoryEntity, CategoryDTO.class);
    }

    public CategoryEntity dtoToEntity(CategoryDTO categoryDTO) {
        return modelMapper.map(categoryDTO, CategoryEntity.class);
    }

}
