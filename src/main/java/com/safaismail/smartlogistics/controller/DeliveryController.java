package com.safaismail.smartlogistics.controller;

import com.safaismail.smartlogistics.model.Delivery;
import com.safaismail.smartlogistics.repository.DeliveryRepository;

import com.safaismail.smartlogistics.exception.DeliveryNotFoundException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.Valid;


import java.util.List;

// Handles API requests related to deliveries
@RestController
@RequestMapping("/api/deliveries")
public class DeliveryController {

    // Repository used to communicate with the PostgreSQL database
    private final DeliveryRepository deliveryRepository;

    // Spring provides the DeliveryRepository when this controller is created
    public DeliveryController(DeliveryRepository deliveryRepository) {
        this.deliveryRepository = deliveryRepository;
    }

    // Returns all deliveries or filters using any combination
    // of status, postcode and customer name
    @GetMapping
    public List<Delivery> getDeliveries(
            @RequestParam(defaultValue = "") String status,
            @RequestParam(defaultValue = "") String postcode,
            @RequestParam(defaultValue = "") String customerName) {

        return deliveryRepository.searchDeliveries(
                status.trim(),
                postcode.trim(),
                customerName.trim()
        );
    }

    // Returns one delivery by its ID
    // Returns one delivery by its ID
    @GetMapping("/{id}")
    public Delivery getDeliveryById(@PathVariable Long id) {

        return deliveryRepository
                .findById(id)
                .orElseThrow(() -> new DeliveryNotFoundException(id));

    }

    // Creates and saves a new delivery
    @PostMapping
    public Delivery createDelivery(
            @Valid @RequestBody Delivery newDelivery) {

        // PostgreSQL generates the ID automatically
        newDelivery.setId(null);

        return deliveryRepository.save(newDelivery);

    }

    // Updates an existing delivery
    // Updates an existing delivery
    @PutMapping("/{id}")
    public Delivery updateDelivery(
            @PathVariable Long id,
            @Valid @RequestBody Delivery updatedDelivery) {

        // Find the existing delivery or return 404 if it does not exist
        Delivery existingDelivery = deliveryRepository
                .findById(id)
                .orElseThrow(() -> new DeliveryNotFoundException(id));

        // Update the existing delivery with the new values
        existingDelivery.setCustomerName(updatedDelivery.getCustomerName());
        existingDelivery.setAddress(updatedDelivery.getAddress());
        existingDelivery.setPostcode(updatedDelivery.getPostcode());
        existingDelivery.setStatus(updatedDelivery.getStatus());

        // Save the updated delivery to PostgreSQL
        return deliveryRepository.save(existingDelivery);
    }

    // Deletes a delivery by its ID
    // Deletes a delivery by its ID
    @DeleteMapping("/{id}")
    public String deleteDelivery(@PathVariable Long id) {

        // If the delivery does not exist, return 404
        if (!deliveryRepository.existsById(id)) {
            throw new DeliveryNotFoundException(id);
        }

        // Delete the delivery from PostgreSQL
        deliveryRepository.deleteById(id);

        return "Delivery deleted successfully";
    }
}