package com.joaDev.apiMarket.controller;

import com.joaDev.apiMarket.common.ApiResponse;
import com.joaDev.apiMarket.dto.ProductDTO;
import com.joaDev.apiMarket.service.product.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/fulltime/api/v1/products")
public class ProductController {

    private final ApiResponse<ProductDTO> apiResponse;
    private final IProductService productService;
    @Autowired
    public ProductController(ApiResponse<ProductDTO> apiResponse, IProductService productService) {
        this.apiResponse = apiResponse;
        this.productService = productService;
    }
    @GetMapping("/list")
    public ResponseEntity<ApiResponse<ProductDTO>> getProductList() {
        List<ProductDTO> productDTOList = this.productService.findAllProducts();
        String message = productDTOList.isEmpty() ? "NO HAY PRODUCTOS EN LA DB" : "PRODUCTOS TRIDOS CON EXITO DE LA DB";
        this.setResponse(productDTOList, message, HttpStatus.OK);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<ApiResponse<ProductDTO>> getProductById(@PathVariable Long id) {
        ProductDTO productDTO = productService.findProductById(id);
        System.out.println(productDTO.getName());
        if (isNull(productDTO)) {
            this.setResponse(List.of(productDTO), "PRODUCTO NO ENCONTRADO EN LA DB", HttpStatus.NOT_FOUND);
            return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
        }
        this.setResponse(List.of(productDTO), "PRODUCTO ENCONTRADO EN LA DB", HttpStatus.OK);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @GetMapping("/product/price/between")
    public ResponseEntity<ApiResponse<ProductDTO>> getProductListBetweenPrice(
            @RequestParam("start") BigDecimal start,
            @RequestParam("end") BigDecimal end
    ) {
        List<ProductDTO> productDTOList = this.productService.finAllProductsBetweenPrice(start, end);
        if(productDTOList.isEmpty()) {
            this.setResponse(null, "NO SE ENCONTRARON COINCIDENCIAS", HttpStatus.NOT_FOUND);
        }
        this.setResponse(productDTOList, "Productos encontrados exitosamente", HttpStatus.OK);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<ApiResponse<ProductDTO>> postProduct(@RequestBody ProductDTO productDTO) {
        this.setResponse(List.of(productDTO), "PRODUCTO CARGADO EXISTOSAMENTE",HttpStatus.OK);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }




    public void setResponse(List<ProductDTO> productDTOList, String message, HttpStatus httpStatus) {
        apiResponse.setData(productDTOList);
        apiResponse.setMessage(message);
        apiResponse.setStatus(HttpStatus.OK);
    }

    public boolean isNull(ProductDTO productDTO) { return productDTO == null; }
}
