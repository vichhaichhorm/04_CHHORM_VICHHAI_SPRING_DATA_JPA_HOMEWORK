package com.product.homework_jpa_product.dto.dtoRequest.productRequest;

import com.product.homework_jpa_product.entities.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DTOProductRequest {
    private String productName;
    private Double unitPrice;
    private String description;
    public void productRequest(Product product){
        product.setName(productName);
        product.setUnitPrice(unitPrice);
        product.setDescription(description);
    }
}
