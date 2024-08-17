package com.product.homework_jpa_product.service.serviceImp;

import com.product.homework_jpa_product.dto.dtoRequest.productRequest.DTOProductRequest;
import com.product.homework_jpa_product.dto.dtoResponse.productResponse.DTOProductResponse;
import com.product.homework_jpa_product.entities.Product;
import com.product.homework_jpa_product.repository.ProductRepository;
import com.product.homework_jpa_product.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductServiceImp implements ProductService {
    private final ProductRepository productRepository;

    @Override
    public DTOProductResponse saveProduct(DTOProductRequest dtoProductRequest) {
        Product product = new Product();
        dtoProductRequest.productRequest(product);
        Product productSaved = productRepository.save(product);
        DTOProductResponse dtoProductResponse = new DTOProductResponse();
        dtoProductResponse.productResponse(productSaved);
        return dtoProductResponse;
    }

    @Override
    public DTOProductResponse getProductById(Long id) {
        Product product = productRepository.findById(id).orElse(null);
        DTOProductResponse dtoProductResponse = new DTOProductResponse();
        dtoProductResponse.productResponse(product);
        return dtoProductResponse;
    }

    @Override
    public Page<Product> getAllProduct(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    @Override
    public Product deleteProductById(Long id) {
        Optional<Product> productOptional = productRepository.findById(id);
        if (productOptional.isPresent()){
            Product productDeleted = productOptional.get();
            productRepository.deleteById(id);
            return productDeleted;
        }else {
            return null;
        }
    }
    @Override
    public DTOProductResponse updateProductById(DTOProductRequest dtoProductRequest, Long id) {
        Product product = productRepository.findById(id).orElse(null);

        if (product != null) {
            dtoProductRequest.productRequest(product);
            Product updatedProduct = productRepository.save(product);
            DTOProductResponse dtoProductResponse = new DTOProductResponse();
            dtoProductResponse.productResponse(updatedProduct);

            return dtoProductResponse;
        }
        return null;
    }




}
