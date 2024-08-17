package com.product.homework_jpa_product.controller;

import com.product.homework_jpa_product.apiResponse.APIResponse;
import com.product.homework_jpa_product.dto.dtoRequest.customerRequest.DTOCustomerRequest;
import com.product.homework_jpa_product.dto.dtoResponse.customerResponse.DTOCustomerResponseOne;
import com.product.homework_jpa_product.dto.dtoResponse.customerResponse.DTOCustomerResponseTwo;
import com.product.homework_jpa_product.dto.dtoResponse.customerResponse.DTOGetCustomerResponseThree;
import com.product.homework_jpa_product.entities.Customer;
import com.product.homework_jpa_product.service.CustomerService;
import lombok.AllArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/api/v1/customer")
@AllArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    @PostMapping("/saveCustomer")
    public ResponseEntity<APIResponse<Object>> saveCustomer(@RequestBody DTOCustomerRequest dtoCustomerRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(customerService.saveCustomer(dtoCustomerRequest).getBody()) ;
    }
    @GetMapping("/getAllCustomer")
    public ResponseEntity<APIResponse<Object>> getAllCustomer(
            @RequestParam(required = false, defaultValue = "1") Integer page,
            @RequestParam(required = false, defaultValue = "10") Integer size,
            @RequestParam(required = false, defaultValue = "id") String sortBy,
            @RequestParam(required = false, defaultValue = "ASC") String direction
    ) {
        Sort sort = direction.equalsIgnoreCase(Sort.Direction.ASC.name())
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page - 1, size, sort);
        Page<DTOCustomerResponseTwo> customerPage = customerService.getAllCustomer(pageable);

        APIResponse<Object> apiResponse = APIResponse.builder()
                .message("Get all customers successfully.")
                .payload(customerPage.getContent())
                .status(HttpStatus.OK)
                .build();
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/getCustomerById/{id}")
    public ResponseEntity<APIResponse<Object>> getCustomerById(@PathVariable Long id){
        Customer findCustomerById = customerService.getCustomerById(id);
        APIResponse<Object> apiResponse = APIResponse.builder()
                .message("Get customer id "+ id +" successfully.")
                .payload(findCustomerById)
                .status(HttpStatus.OK)
                .build();
        return ResponseEntity.ok(apiResponse);
    }

    @DeleteMapping("/deleteCustomerById/{id}")
    public ResponseEntity<APIResponse<Object>> deleteCustomerById(@PathVariable Long id) {
        Customer deletedCustomer = customerService.deleteCustomerById(id);
        if (deletedCustomer == null) {
            APIResponse<Object> apiResponse = APIResponse.builder()
                    .message("Customer with id " + id + " not found.")
                    .status(HttpStatus.NOT_FOUND)
                    .build();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiResponse);
        }
        APIResponse<Object> apiResponse = APIResponse.builder()
                .message("Customer with id " + id + " is deleted successfully.")
                .status(HttpStatus.OK)
                .build();
        return ResponseEntity.ok(apiResponse);
    }

    @PutMapping("/updateCustomerById/{id}")
    public ResponseEntity<APIResponse<DTOGetCustomerResponseThree>> updateCustomerById(
            @RequestBody DTOCustomerRequest dtoCustomerRequest,
            @PathVariable Long id
    ) {
        DTOGetCustomerResponseThree updatedCustomerDto = customerService.updateCustomerById(dtoCustomerRequest, id);
        if (updatedCustomerDto == null) {
            APIResponse<DTOGetCustomerResponseThree> apiResponse = APIResponse.<DTOGetCustomerResponseThree>builder()
                    .message("Customer with ID " + id + " not found.")
                    .status(HttpStatus.NOT_FOUND)
                    .build();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiResponse);
        }
        APIResponse<DTOGetCustomerResponseThree> apiResponse = APIResponse.<DTOGetCustomerResponseThree>builder()
                .message("Customer with ID " + id + " updated successfully.")
                .payload(updatedCustomerDto)
                .status(HttpStatus.OK)
                .build();
        return ResponseEntity.ok(apiResponse);
    }

}
