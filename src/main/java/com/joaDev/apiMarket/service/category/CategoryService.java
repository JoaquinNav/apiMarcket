package com.joaDev.apiMarket.service.category;


import com.joaDev.apiMarket.dto.CategoryDTO;

import java.util.List;

public interface CategoryService {
    List<CategoryDTO> getAllCategories();
    CategoryDTO getCategoryById(Long id);
    List<CategoryDTO> getCategoryByName(String description);
    CategoryDTO createCategory(CategoryDTO categoryDTO);
}
