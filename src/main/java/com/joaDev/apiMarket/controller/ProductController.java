package com.joaDev.apiMarket.controller;

import com.joaDev.apiMarket.common.ApiResponse;
import com.joaDev.apiMarket.dto.ProductDTO;
import com.joaDev.apiMarket.service.product.IProductService;
import jakarta.validation.constraints.NotNull;
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
        else this.setResponse(productDTOList, "Productos encontrados exitosamente", HttpStatus.OK);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @GetMapping("/product/name/{name}")
    public ResponseEntity<ApiResponse<ProductDTO>> getProductListByName(@PathVariable String name) {
        List<ProductDTO> filteredProductsByName = this.productService.findAllProductsByName(name);
        if(filteredProductsByName.isEmpty())
            this.setResponse(filteredProductsByName, "NO EXISTEN PRODUCTOS CON NOMBRE: "+name, HttpStatus.OK);
        else this.setResponse(filteredProductsByName, "PRODUCTOS FILTRADOS POR: "+name, HttpStatus.OK);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<ApiResponse<ProductDTO>> postProduct(@RequestBody ProductDTO productDTO) {
        //TODO CORROBORAR CATEGORY
        ProductDTO savedProduct = this.productService.createProduct(productDTO);
        if(isNull(savedProduct)) {
            this.setResponse(List.of(productDTO), "NO SE HA PODIDO CREAR EL PRODUCTO",HttpStatus.NOT_FOUND);
        }
        else this.setResponse(List.of(productDTO), "PRODUCTO CARGADO EXISTOSAMENTE",HttpStatus.OK);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @DeleteMapping("/delete/id/{id}")
    public ResponseEntity<ApiResponse<ProductDTO>> deleteProductById(@PathVariable Long id) {
        ProductDTO deletedProduct = this.productService.deleteProductById(id);
        if (isNull(deletedProduct)) {
            this.setResponse(null, "El Producto con el id: "+id+" no se encuentra en la db",HttpStatus.NOT_FOUND);
        }
        else this.setResponse(List.of(deletedProduct), "El Producto con el id: "+id+" fue eliminado existosamente",HttpStatus.OK);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @DeleteMapping("/delete/name/{name}")
    public ResponseEntity<ApiResponse<ProductDTO>> deleteProductByName(@PathVariable String name) {
        ProductDTO deletedProduct = this.productService.deleteProductByName(name);
        if (isNull(deletedProduct)) {
            this.setResponse(null, "El Producto con el nombre: "+name+" no se encuentra en la db",HttpStatus.NOT_FOUND);
        }
        else this.setResponse(List.of(deletedProduct), "El Producto con el id: "+name+" fue eliminado existosamente",HttpStatus.OK);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<ApiResponse<ProductDTO>> editProduct(@PathVariable @NotNull Long id, @RequestBody ProductDTO productDTO) {
        ProductDTO updatedProduct = this.productService.updateProduct(id, productDTO);
        if(isNull(updatedProduct))
            this.setResponse(null, "El Producto con el id: "+id+" no se encuentra en la db",HttpStatus.NOT_FOUND);
        else this.setResponse(List.of(), "El Producto con el id: "+id+" fue editado existosamente",HttpStatus.OK);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    public void setResponse(List<ProductDTO> productDTOList, String message, HttpStatus httpStatus) {
        apiResponse.setData(productDTOList);
        apiResponse.setMessage(message);
        apiResponse.setStatus(HttpStatus.OK);
    }

    public boolean isNull(ProductDTO productDTO) { return productDTO == null; }
}
