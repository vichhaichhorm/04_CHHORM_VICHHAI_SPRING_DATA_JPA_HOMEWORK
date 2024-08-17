package com.product.homework_jpa_product.service.serviceImp;

import com.product.homework_jpa_product.apiResponse.APIResponse;
import com.product.homework_jpa_product.dto.dtoRequest.customerRequest.DTOCustomerRequest;
import com.product.homework_jpa_product.dto.dtoResponse.customerResponse.DTOCustomerResponseOne;
import com.product.homework_jpa_product.dto.dtoResponse.customerResponse.DTOCustomerResponseTwo;
import com.product.homework_jpa_product.dto.dtoResponse.customerResponse.DTOGetCustomerResponseThree;
import com.product.homework_jpa_product.entities.Customer;
import com.product.homework_jpa_product.repository.CustomerRepository;
import com.product.homework_jpa_product.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CustomerServiceImp implements CustomerService {
    private final CustomerRepository customerRepository;
    @Override
    public ResponseEntity<APIResponse<Object>> saveCustomer(DTOCustomerRequest dtoCustomerRequest) {
        Customer customer = new Customer();
        dtoCustomerRequest.requestCustomer(customer);
        Customer saveCustomer = customerRepository.save(customer);
        DTOCustomerResponseOne dtoCustomerResponse = new DTOCustomerResponseOne();
        dtoCustomerResponse.responseCustomer(saveCustomer);
        APIResponse<Object> apiResponse = APIResponse.builder()
                .message("A new customer is inserted successfully.")
                .payload(dtoCustomerResponse)
                .status(HttpStatus.CREATED)
                .build();
        return ResponseEntity.ok(apiResponse);
    }

    @Override
    public Page<DTOCustomerResponseTwo> getAllCustomer(Pageable pageable) {
        Page<Customer> customerPage = customerRepository.findAll(pageable);

        List<DTOCustomerResponseTwo> dtoCustomerResponseList = new ArrayList<>();
        for (Customer customer : customerPage) {
            DTOCustomerResponseTwo dtoCustomerResponseTwo = new DTOCustomerResponseTwo();
            dtoCustomerResponseTwo.responseCustomerWithList(customer);
            dtoCustomerResponseList.add(dtoCustomerResponseTwo);
        }

        return new PageImpl<>(dtoCustomerResponseList, pageable, customerPage.getTotalElements());
    }


    @Override
    public Customer getCustomerById(Long id) {
        return customerRepository.findById(id).orElse(null);
    }

    @Override
    public Customer deleteCustomerById(Long id) {
        Optional<Customer> customerOptional = customerRepository.findById(id);
        if (customerOptional.isPresent()) {
            Customer customer = customerOptional.get();
            customerRepository.deleteById(id);
            return customer;
        } else {
            return null;
        }

    }

    @Override
    public DTOGetCustomerResponseThree updateCustomerById(DTOCustomerRequest dtoCustomerRequest, Long id) {
        Optional<Customer> customerOptional = customerRepository.findById(id);
        if (customerOptional.isPresent()) {
            Customer customer = customerOptional.get();
            customer.setName(dtoCustomerRequest.getCustomerName());
            customer.setAddress(dtoCustomerRequest.getAddress());
            customer.setPhoneNumber(dtoCustomerRequest.getPhoneNumber());
            if (customer.getEmail() != null) {
                customer.getEmail().setEmail(dtoCustomerRequest.getEmail());
            }
            customerRepository.save(customer);
            DTOGetCustomerResponseThree dtoCustomerResponse = new DTOGetCustomerResponseThree();
            dtoCustomerResponse.responseCustomerThree(customer);
            return dtoCustomerResponse;
        } else {
            return null;
        }
    }

}
