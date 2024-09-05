package com.joaDev.apiMarket.service.category;

import com.joaDev.apiMarket.dto.CategoryDTO;
import com.joaDev.apiMarket.repository.CategoryRepository;
import org.modelmapper.ModelMapper;

import java.util.List;

public class CategoryServiceImp implements CategoryService{

    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    public CategoryServiceImp(CategoryRepository categoryRepository, ModelMapper modelMapper) {
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<CategoryDTO> getAllCategories() {
        return this.categoryRepository
                .findAll()
                .stream()
                .map(categoryEntity -> modelMapper.map(categoryEntity, CategoryDTO.class))
                .toList();
    }
}
