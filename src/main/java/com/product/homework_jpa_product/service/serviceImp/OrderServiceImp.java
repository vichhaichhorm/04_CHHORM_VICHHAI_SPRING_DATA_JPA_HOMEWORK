package com.product.homework_jpa_product.service.serviceImp;

import com.product.homework_jpa_product.dto.dtoRequest.productOrderRequest.ProductOrderRequests;
import com.product.homework_jpa_product.dto.dtoResponse.orderResponse.DTOGetOrderResponse;
import com.product.homework_jpa_product.dto.dtoResponse.orderResponse.DTOOrderResponse;
import com.product.homework_jpa_product.dto.dtoResponse.productResponse.DTOProductResponse;
import com.product.homework_jpa_product.dto.enums.Status;
import com.product.homework_jpa_product.entities.Customer;
import com.product.homework_jpa_product.entities.Order;
import com.product.homework_jpa_product.entities.Product;
import com.product.homework_jpa_product.entities.ProductOrder;
import com.product.homework_jpa_product.repository.CustomerRepository;
import com.product.homework_jpa_product.repository.OrderRepository;
import com.product.homework_jpa_product.repository.ProductRepository;
import com.product.homework_jpa_product.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OrderServiceImp implements OrderService {
    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;

    @Override
    public DTOOrderResponse saveOrder(Long customerId, List<ProductOrderRequests> productOrderRequests) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));
        Order order = new Order();
        order.setOrderDate(LocalDate.now());
        order.setCustomer(customer);
        order.setStatus(Status.PENDING);
        List<ProductOrder> productOrders = new ArrayList<>();
        // Calculate
        float totalAmount = 0;
        for (ProductOrderRequests request : productOrderRequests) {
            Product product = productRepository.findById(request.getProductId())
                    .orElseThrow(() -> new RuntimeException("Product not found"));
            ProductOrder productOrder = new ProductOrder();
            productOrder.setProduct(product);
            productOrder.setOrder(order);
            productOrder.setQuantity(request.getQuantity());
            productOrders.add(productOrder);

            totalAmount += (float) (product.getUnitPrice() * request.getQuantity());
        }

        order.setTotalAmount(totalAmount);
        order.setProductOrderList(productOrders);
        // Save the order
        Order savedOrder = orderRepository.save(order);
        // Convert the saved Order to DTOOrderResponse
        DTOOrderResponse orderResponse = new DTOOrderResponse();
        orderResponse.responseOrder(savedOrder);
        List<DTOProductResponse> productResponses = savedOrder.getProductOrderList().stream()
                .map(productOrder -> {
                    DTOProductResponse productResponse = new DTOProductResponse();
                    productResponse.productResponse(productOrder.getProduct());
                    return productResponse;
                }).collect(Collectors.toList());
        orderResponse.setProductList(productResponses);
        return orderResponse;
    }

    @Override
    public DTOGetOrderResponse getOrderById(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));
        DTOGetOrderResponse orderResponse = new DTOGetOrderResponse();
        orderResponse.responseGetOrder(order);

        List<DTOProductResponse> productResponses = order.getProductOrderList().stream()
                .map(productOrder -> {
                    DTOProductResponse productResponse = new DTOProductResponse();
                    productResponse.productResponse(productOrder.getProduct());
                    return productResponse;
                }).collect(Collectors.toList());

        orderResponse.setProductList(productResponses);
        return orderResponse;
    }

    @Override
    public DTOGetOrderResponse updateOrder(Status status, Long orderId) {
        Order order = orderRepository.findById(orderId).orElse(null);
        if (order != null) {
            order.setStatus(status);
            Order updatedOrder = orderRepository.save(order);
            // convert to dto
            DTOGetOrderResponse dtoGetOrderResponse = new DTOGetOrderResponse();
            dtoGetOrderResponse.responseGetOrder(updatedOrder);
            return dtoGetOrderResponse;
        }
        return null;
    }

    @Override
    public DTOGetOrderResponse getOrderByCustomerId(Long customerId) {
        List<Order> orders = orderRepository.findByCustomerId(customerId);

        if (orders.isEmpty()) {
            return null;
        }
        // Convert to DTOGetOrderResponse
        Order order = orders.get(0);
        DTOGetOrderResponse dtoGetOrderResponse = new DTOGetOrderResponse();
        dtoGetOrderResponse.responseGetOrder(order);
        return dtoGetOrderResponse;
    }

}
