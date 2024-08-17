package com.product.homework_jpa_product.service;

import com.product.homework_jpa_product.dto.dtoRequest.productRequest.DTOProductRequest;
import com.product.homework_jpa_product.dto.dtoResponse.productResponse.DTOProductResponse;
import com.product.homework_jpa_product.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {
    DTOProductResponse saveProduct(DTOProductRequest dtoProductRequest);

    DTOProductResponse getProductById(Long id);

    Page<Product> getAllProduct(Pageable pageable);

    Product deleteProductById(Long id);

    DTOProductResponse updateProductById(DTOProductRequest dtoProductRequest, Long id);
}
