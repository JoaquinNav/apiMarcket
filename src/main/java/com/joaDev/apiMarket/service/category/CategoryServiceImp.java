package com.joaDev.apiMarket.service.category;

import com.joaDev.apiMarket.dto.CategoryDTO;
import com.joaDev.apiMarket.repository.CategoryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
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
