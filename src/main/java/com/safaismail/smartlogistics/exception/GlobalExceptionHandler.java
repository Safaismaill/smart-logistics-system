package com.safaismail.smartlogistics.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

// Handles errors that occur across the REST API
@RestControllerAdvice
public class GlobalExceptionHandler {

    // Handles validation errors caused by @Valid
    // Handles cases where a delivery ID does not exist
    @ExceptionHandler(DeliveryNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleDeliveryNotFound(
            DeliveryNotFoundException exception) {

        Map<String, String> error = new HashMap<>();

        error.put("error", exception.getMessage());

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(error);

    }
}