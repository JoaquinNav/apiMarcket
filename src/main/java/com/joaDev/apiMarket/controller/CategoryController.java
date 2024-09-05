package com.joaDev.apiMarket.controller;


import com.joaDev.apiMarket.common.ApiResponse;
import com.joaDev.apiMarket.dto.CategoryDTO;
import com.joaDev.apiMarket.service.category.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/fulltime/api/v1/category")
public class CategoryController {

    private final CategoryService categoryService;
    private final ApiResponse<CategoryDTO> apiResponse;

    @Autowired
    public CategoryController(CategoryService categoryService, ApiResponse<CategoryDTO> apiResponse) {
        this.categoryService = categoryService;
        this.apiResponse = apiResponse;
    }


    @GetMapping("/list")
    public ResponseEntity<ApiResponse<CategoryDTO>> getAllCategories() {
        List<CategoryDTO> categoryList = this.categoryService.getAllCategories();
        if(categoryList == null)
            this.setApiResponse(categoryList, "NO EXISTEN CATEGORIAS", HttpStatus.NO_CONTENT);
        else this.setApiResponse(categoryList, "CATEGORIAS", HttpStatus.OK);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<CategoryDTO>> getCategoryById(@PathVariable Long id) {
        CategoryDTO category = this.categoryService.getCategoryById(id);
        if(category == null)
            this.setApiResponse(null, "NO EXISTE CATEGORIA", HttpStatus.NOT_FOUND);
        else this.setApiResponse(List.of(category), "CATEGORIAS", HttpStatus.OK);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @GetMapping("/description/{description}")
    public ResponseEntity<ApiResponse<CategoryDTO>> getCategoryByDescription(@PathVariable String description) {
        List<CategoryDTO> categories = this.categoryService.getCategoryByName(description);
        if(categories.isEmpty())
            this.setApiResponse(null, "NO EXISTE CATEGORIA", HttpStatus.NOT_FOUND);
        else this.setApiResponse(categories, "CATEGORIA ENCONTRADA", HttpStatus.OK);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }



    public void setApiResponse(List<CategoryDTO> categoryDTOList, String message, HttpStatus status) {
        apiResponse.setData(categoryDTOList);
        apiResponse.setMessage(message);
        apiResponse.setStatus(status);
    }

}
