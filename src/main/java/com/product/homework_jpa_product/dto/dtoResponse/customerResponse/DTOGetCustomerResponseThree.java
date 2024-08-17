package com.product.homework_jpa_product.dto.dtoResponse.customerResponse;

import com.product.homework_jpa_product.entities.Customer;
import com.product.homework_jpa_product.entities.Email;
import com.product.homework_jpa_product.entities.Order;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DTOGetCustomerResponseThree {
    private Long id;
    private String name;
    private String address;
    private String phoneNumber;
    private Email email;
    List<Order> orderList = new ArrayList<>();
    public void responseCustomerThree(Customer customer){
        id = customer.getId();
        name = customer.getName();
        address = customer.getAddress();
        phoneNumber = customer.getPhoneNumber();
        email = customer.getEmail();
    }
}
