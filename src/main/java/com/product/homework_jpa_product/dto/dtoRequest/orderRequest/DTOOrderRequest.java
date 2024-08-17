package com.product.homework_jpa_product.dto.dtoRequest.orderRequest;

import com.product.homework_jpa_product.entities.Order;
import com.product.homework_jpa_product.entities.ProductOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DTOOrderRequest {
    private int quantity;
    private Long productId;
    public void requestOrder(ProductOrder productOrder){
        productOrder.setId(productId);
    }
}
