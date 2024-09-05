package com.joaDev.apiMarket.dto;
import com.joaDev.apiMarket.model.ProductEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDTO {
    private Long idCategory;
    private List<ProductEntity> productList;
}
