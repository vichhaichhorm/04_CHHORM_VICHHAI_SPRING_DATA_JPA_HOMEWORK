package com.product.homework_jpa_product.dto.dtoResponse.customerResponse;

import com.product.homework_jpa_product.dto.dtoResponse.orderResponse.DTOOrderResponse;
import com.product.homework_jpa_product.entities.Customer;
import com.product.homework_jpa_product.entities.Order;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DTOCustomerResponseTwo {
    private DTOCustomerResponseOne customer;
    private List<DTOOrderResponse> orderList;

    public void responseCustomerWithList(Customer customer) {
        this.customer = new DTOCustomerResponseOne();
        this.customer.responseCustomer(customer);

        this.orderList = new ArrayList<>();
        for (Order order : customer.getOrderList()) {
            DTOOrderResponse dtoOrderResponse = new DTOOrderResponse();
            dtoOrderResponse.responseOrder(order);
            this.orderList.add(dtoOrderResponse);
        }
    }

}
