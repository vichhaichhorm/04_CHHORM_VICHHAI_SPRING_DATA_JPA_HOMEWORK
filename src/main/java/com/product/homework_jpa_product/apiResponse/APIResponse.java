package com.product.homework_jpa_product.apiResponse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class APIResponse<T> {
    private HttpStatus status;
    private String message;
    private T payload;
}
