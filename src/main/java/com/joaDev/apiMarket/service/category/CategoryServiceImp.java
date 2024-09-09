package com.joaDev.apiMarket.service.category;

import com.joaDev.apiMarket.dto.CategoryDTO;
import com.joaDev.apiMarket.dto.ProductDTO;
import com.joaDev.apiMarket.model.CategoryEntity;
import com.joaDev.apiMarket.repository.CategoryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImp implements CategoryService{

    private final CategoryRepository categoryRepository;
    private final ProductDTO productDTO;
    private final ModelMapper modelMapper;

    public CategoryServiceImp(CategoryRepository categoryRepository, ProductDTO productDTO, ModelMapper modelMapper) {
        this.categoryRepository = categoryRepository;
        this.productDTO = productDTO;
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

    @Override
    public CategoryDTO getCategoryById(Long id) {
        CategoryEntity category = this.categoryRepository.findById(id).orElse(null);
        return category == null ? null : this.modelMapper.map(category, CategoryDTO.class);
    }

    @Override
    public List<CategoryDTO> getCategoryByName(String description) {
        return this.categoryRepository
                .findAll()
                .stream()
                .filter(categoryEntity -> categoryEntity.getDescription().contains(description))
                .map(categoryEntity -> modelMapper.map(categoryEntity, CategoryDTO.class))
                .toList();
    }
    @Override
    public CategoryDTO createCategory(CategoryDTO categoryDTO) {
        if(categoryDTO == null || categoryDTO.getIdCategory() == null) return null;
        CategoryEntity newCategory = this.modelMapper.map(categoryDTO, CategoryEntity.class);
        this.categoryRepository.save(newCategory);
        return modelMapper.map(newCategory, CategoryDTO.class);
    }
}
