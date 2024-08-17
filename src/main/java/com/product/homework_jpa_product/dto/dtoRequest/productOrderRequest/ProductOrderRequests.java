package com.product.homework_jpa_product.dto.dtoRequest.productOrderRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductOrderRequests {
    private Long productId;
    private int quantity;
}
