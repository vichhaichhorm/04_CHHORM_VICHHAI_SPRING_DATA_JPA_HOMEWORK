package com.product.homework_jpa_product.dto.dtoResponse.customerResponse;

import com.product.homework_jpa_product.entities.Customer;
import com.product.homework_jpa_product.entities.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DTOCustomerResponseOne {
    private Long id;
    private String name;
    private String address;
    private String phoneNumber;
    private Email email;
    public void responseCustomer(Customer customer){
        id = customer.getId();
        name = customer.getName();
        address = customer.getAddress();
        phoneNumber = customer.getPhoneNumber();
        email = customer.getEmail();
    }

}

