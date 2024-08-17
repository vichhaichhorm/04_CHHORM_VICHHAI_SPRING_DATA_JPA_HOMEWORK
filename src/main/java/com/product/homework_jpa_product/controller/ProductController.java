package com.product.homework_jpa_product.controller;

import com.product.homework_jpa_product.apiResponse.APIResponse;
import com.product.homework_jpa_product.dto.dtoRequest.productRequest.DTOProductRequest;
import com.product.homework_jpa_product.dto.dtoResponse.productResponse.DTOProductResponse;
import com.product.homework_jpa_product.entities.Product;
import com.product.homework_jpa_product.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/products")
public class ProductController {
    private final ProductService productService;

    @PostMapping("/saveProduct")
    public ResponseEntity<APIResponse<DTOProductResponse>> saveProduct(@RequestBody DTOProductRequest dtoProductRequest) {
        DTOProductResponse dtoProductResponse = productService.saveProduct(dtoProductRequest);
        APIResponse<DTOProductResponse> apiResponse = APIResponse.<DTOProductResponse>builder()
                .message("A new product is inserted successfully")
                .payload(dtoProductResponse)
                .status(HttpStatus.CREATED)
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
    }

    @GetMapping("getProductById/{id}")
    public ResponseEntity<APIResponse<Object>> getProductById(@PathVariable Long id){
        DTOProductResponse product = productService.getProductById(id);
        if (Objects.isNull(product)){
            APIResponse<Object> apiResponse = APIResponse.builder()
                    .message("Not found with id")
                    .status(HttpStatus.NOT_FOUND)
                    .build();
            return ResponseEntity.ok(apiResponse);
        }else {
            APIResponse<Object> apiResponse = APIResponse.builder()
                    .message("Get product with id "+id+" successfully.")
                    .payload(product)
                    .status(HttpStatus.OK)
                    .build();
            return ResponseEntity.ok(apiResponse);
        }
    }
    @GetMapping("/getALlProduct")
    public ResponseEntity<APIResponse<Object>> getAllProduct(
            @RequestParam (defaultValue = "0") int page,
            @RequestParam (defaultValue = "3") int size,
            @RequestParam (defaultValue = "id") String sortBy,
            @RequestParam (defaultValue = "ASC") String direction
    ){
        Sort sort = direction.equalsIgnoreCase(Sort.Direction.DESC.name())
                ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Product> productPage = productService.getAllProduct(pageable);
        List<Product> productList = productPage.getContent();
        List<DTOProductResponse> dtoProductResponse = new ArrayList<>();
        for (Product product: productList) {
            DTOProductResponse dtoProductResponse1 = new DTOProductResponse();
            dtoProductResponse1.productResponse(product);
            dtoProductResponse.add(dtoProductResponse1);
        }
        APIResponse<Object> apiResponse = APIResponse.builder()
                .message("Get all products successfully.")
                .payload(dtoProductResponse)
                .status(HttpStatus.OK)
                .build();
        return ResponseEntity.ok(apiResponse);
    }

    @DeleteMapping("/deleteProductById/{id}")
    public ResponseEntity<APIResponse<Object>> deleteProductById(@PathVariable Long id){
            productService.deleteProductById(id);
            APIResponse<Object> apiResponse = APIResponse.builder()
                    .message("Product with id "+ id +" is deleted successfully.")
                    .status(HttpStatus.OK)
                    .build();
            return ResponseEntity.ok(apiResponse);
    }

    @PutMapping("/updateProductById/{id}")
    public ResponseEntity<APIResponse<DTOProductResponse>> updateProductById(
            @RequestBody DTOProductRequest dtoProductRequest,
            @PathVariable Long id
    ) {
        DTOProductResponse updatedProductDto = productService.updateProductById(dtoProductRequest, id);
        if (updatedProductDto == null) {
            APIResponse<DTOProductResponse> apiResponse = APIResponse.<DTOProductResponse>builder()
                    .message("Product with ID " + id + " not found.")
                    .status(HttpStatus.NOT_FOUND)
                    .build();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiResponse);
        }
        APIResponse<DTOProductResponse> apiResponse = APIResponse.<DTOProductResponse>builder()
                .message("Update successful.")
                .payload(updatedProductDto)
                .status(HttpStatus.OK)
                .build();
        return ResponseEntity.ok(apiResponse);
    }

}



