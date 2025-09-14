package com.devstack.ecommerce.order_service_api.adviser;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.devstack.ecommerce.order_service_api.exception.EntryNotFoundException;
import com.devstack.ecommerce.order_service_api.util.StandardResponseDto;

@RestControllerAdvice
public class AppWideExceptionHandler {
    @ExceptionHandler(EntryNotFoundException.class)
    public ResponseEntity<StandardResponseDto> handlEntryNotFoundException(EntryNotFoundException e) {
        return new ResponseEntity<>(
            new StandardResponseDto(
                404, e.getMessage(), e
            ), HttpStatus.NOT_FOUND
        );
    }
}
