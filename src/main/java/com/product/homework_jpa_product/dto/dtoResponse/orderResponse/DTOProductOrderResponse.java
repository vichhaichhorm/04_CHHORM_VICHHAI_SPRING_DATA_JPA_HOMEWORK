package com.product.homework_jpa_product.dto.dtoResponse.orderResponse;

import com.product.homework_jpa_product.dto.dtoResponse.productResponse.DTOProductResponse;
import com.product.homework_jpa_product.entities.ProductOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DTOProductOrderResponse {
    private Long id;
    private String name;
    private int quantity;

    public void orderResponseProduct(ProductOrder productOrder){
        id = productOrder.getId();
        name = productOrder.getProduct().getName();
        quantity = productOrder.getQuantity();
    }
}
