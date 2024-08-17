package com.product.homework_jpa_product.dto.dtoRequest.customerRequest;

import com.product.homework_jpa_product.entities.Customer;
import com.product.homework_jpa_product.entities.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DTOCustomerRequest {
    private String customerName;
    private String address;
    private String phoneNumber;
    private String email;
    public void requestCustomer(Customer customer){
        customer.setName(customerName);
        customer.setAddress(address);
        customer.setPhoneNumber(phoneNumber);
        customer.setEmail(new Email(null,email));
    }

}
