package com.product.homework_jpa_product.dto.dtoResponse.orderResponse;

import com.product.homework_jpa_product.dto.dtoResponse.productResponse.DTOProductResponse;
import com.product.homework_jpa_product.entities.Order;
import com.product.homework_jpa_product.entities.ProductOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DTOOrderResponse {
    private Long id;
    private LocalDate orderDate;
    private float totalAmount;
    private String status;
    private List<DTOProductResponse> productList = new ArrayList<>();

    public void responseOrder(Order order) {
        this.id = order.getId();
        this.orderDate = order.getOrderDate();
        this.totalAmount = order.getTotalAmount();
        this.status = order.getStatus().toString();
        for (ProductOrder productOrder : order.getProductOrderList()) {
            DTOProductResponse dtoProductResponse = new DTOProductResponse();
            dtoProductResponse.productResponse(productOrder.getProduct());
            this.productList.add(dtoProductResponse);
        }
    }
}
