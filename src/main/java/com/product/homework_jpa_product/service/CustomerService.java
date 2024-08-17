package com.product.homework_jpa_product.service;

import com.product.homework_jpa_product.apiResponse.APIResponse;
import com.product.homework_jpa_product.dto.dtoRequest.customerRequest.DTOCustomerRequest;
import com.product.homework_jpa_product.dto.dtoResponse.customerResponse.DTOCustomerResponseTwo;
import com.product.homework_jpa_product.dto.dtoResponse.customerResponse.DTOGetCustomerResponseThree;
import com.product.homework_jpa_product.entities.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface CustomerService {
    ResponseEntity<APIResponse<Object>> saveCustomer(DTOCustomerRequest dtoCustomerRequest);

    Page<DTOCustomerResponseTwo> getAllCustomer(Pageable pageable);

    Customer getCustomerById(Long id);

    Customer deleteCustomerById(Long id);

    DTOGetCustomerResponseThree updateCustomerById(DTOCustomerRequest dtoCustomerRequest, Long id);
}
