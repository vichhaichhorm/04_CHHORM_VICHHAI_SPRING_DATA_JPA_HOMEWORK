package com.product.homework_jpa_product.controller;

import com.product.homework_jpa_product.apiResponse.APIResponse;
import com.product.homework_jpa_product.dto.dtoRequest.productOrderRequest.ProductOrderRequests;
import com.product.homework_jpa_product.dto.dtoResponse.orderResponse.DTOGetOrderResponse;
import com.product.homework_jpa_product.dto.dtoResponse.orderResponse.DTOOrderResponse;
import com.product.homework_jpa_product.dto.enums.Status;
import com.product.homework_jpa_product.entities.Order;
import com.product.homework_jpa_product.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/order")
public class OrderController {
    private final OrderService orderService;

    @PostMapping("/saveOrder/{customerId}")
    public ResponseEntity<APIResponse<Object>> saveOrder(
            @PathVariable Long customerId,
            @RequestBody List<ProductOrderRequests> productOrderRequests) {
        DTOOrderResponse orderResponse = orderService.saveOrder(customerId, productOrderRequests);
        APIResponse<Object> apiResponse = APIResponse.builder()
                .message("A new order is created successfully.")
                .payload(orderResponse)
                .status(HttpStatus.OK)
                .build();

        return ResponseEntity.ok(apiResponse);
    }
    @GetMapping("/getOrderById/{orderId}")
    public ResponseEntity<APIResponse<Object>> getOrderById(@PathVariable Long orderId) {
        DTOGetOrderResponse orderResponse = orderService.getOrderById(orderId);
        APIResponse<Object> apiResponse = APIResponse.builder()
                .message("Get order id " + orderId + " successfully.")
                .payload(orderResponse)
                .status(HttpStatus.OK)
                .build();
        return ResponseEntity.ok(apiResponse);
    }

    @PutMapping("/updateOrder/status")
    public ResponseEntity<APIResponse<DTOGetOrderResponse>> updateOrder(
            @RequestParam Status status,
            @RequestParam Long orderId
    ) {
        DTOGetOrderResponse updatedOrderDto = orderService.updateOrder(status, orderId);
        if (updatedOrderDto == null) {
            APIResponse<DTOGetOrderResponse> apiResponse = APIResponse.<DTOGetOrderResponse>builder()
                    .message("Order with ID " + orderId + " not found.")
                    .status(HttpStatus.NOT_FOUND)
                    .build();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiResponse);
        }
        APIResponse<DTOGetOrderResponse> apiResponse = APIResponse.<DTOGetOrderResponse>builder()
                .message("Successfully updated the status of order to " + status)
                .payload(updatedOrderDto)
                .status(HttpStatus.OK)
                .build();
        return ResponseEntity.ok(apiResponse);
    }
    @GetMapping("/getOrderByCustomerId/{customerId}")
    public ResponseEntity<APIResponse<DTOGetOrderResponse>> getOrderByCustomerId(@PathVariable Long customerId) {
        DTOGetOrderResponse dtoGetOrderResponse = orderService.getOrderByCustomerId(customerId);
        if (dtoGetOrderResponse == null) {
            APIResponse<DTOGetOrderResponse> apiResponse = APIResponse.<DTOGetOrderResponse>builder()
                    .message("No orders found for customer with ID " + customerId)
                    .status(HttpStatus.NOT_FOUND)
                    .build();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiResponse);
        }
        APIResponse<DTOGetOrderResponse> apiResponse = APIResponse.<DTOGetOrderResponse>builder()
                .message("Get all orders with customer id "+ customerId +" successfully. ")
                .payload(dtoGetOrderResponse)
                .status(HttpStatus.OK)
                .build();
        return ResponseEntity.ok(apiResponse);
    }




}
