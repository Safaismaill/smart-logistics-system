package com.safaismail.smartlogistics.exception;

// Custom exception used when a delivery cannot be found
public class DeliveryNotFoundException extends RuntimeException {

    public DeliveryNotFoundException(Long id) {
        super("Delivery with ID " + id + " was not found");
    }
}