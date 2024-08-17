package com.product.homework_jpa_product.repository;

import com.product.homework_jpa_product.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
