package com.product.homework_jpa_product.dto.dtoResponse.productResponse;

import com.product.homework_jpa_product.entities.Product;
import com.product.homework_jpa_product.entities.ProductOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class DTOProductResponse {
    private Long productId;
    private String productName;
    private Double unitPrice;
    private String description;
    public void productResponse(Product product) {
        productId = product.getId();
        productName = product.getName();
        unitPrice = product.getUnitPrice();
        description = product.getDescription();
    }

}
