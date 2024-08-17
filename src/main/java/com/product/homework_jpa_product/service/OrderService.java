package com.product.homework_jpa_product.service;

import com.product.homework_jpa_product.dto.dtoRequest.productOrderRequest.ProductOrderRequests;
import com.product.homework_jpa_product.dto.dtoResponse.orderResponse.DTOGetOrderResponse;
import com.product.homework_jpa_product.dto.dtoResponse.orderResponse.DTOOrderResponse;
import com.product.homework_jpa_product.dto.enums.Status;
import com.product.homework_jpa_product.entities.Order;

import java.util.List;

public interface OrderService {

    DTOOrderResponse saveOrder(Long customerId, List<ProductOrderRequests> productOrderRequests);

    DTOGetOrderResponse getOrderById(Long orderId);

    DTOGetOrderResponse updateOrder(Status status, Long orderId);

    DTOGetOrderResponse getOrderByCustomerId(Long customerId);
}
