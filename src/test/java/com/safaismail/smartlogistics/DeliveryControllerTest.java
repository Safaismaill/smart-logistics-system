package com.safaismail.smartlogistics;

import com.safaismail.smartlogistics.controller.DeliveryController;
import com.safaismail.smartlogistics.exception.DeliveryNotFoundException;
import com.safaismail.smartlogistics.model.Delivery;
import com.safaismail.smartlogistics.repository.DeliveryRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class DeliveryControllerTest {

    private DeliveryRepository deliveryRepository;
    private DeliveryController deliveryController;

    // Runs before each test
    @BeforeEach
    void setUp() {

        // Create a fake repository
        deliveryRepository = mock(DeliveryRepository.class);

        // Give the fake repository to the controller
        deliveryController =
                new DeliveryController(deliveryRepository);
    }

    @Test
    void shouldReturnDeliveryWhenIdExists() {

        // Arrange - create test data
        Delivery delivery = new Delivery(
                "John Smith",
                "15 King Street",
                "M2 4AA",
                "PENDING"
        );

        delivery.setId(1L);

        // Tell the fake repository what to return
        when(deliveryRepository.findById(1L))
                .thenReturn(Optional.of(delivery));

        // Act - call the controller method
        Delivery result =
                deliveryController.getDeliveryById(1L);

        // Assert - check the result
        assertEquals(1L, result.getId());
        assertEquals("John Smith", result.getCustomerName());
        assertEquals("15 King Street", result.getAddress());
        assertEquals("M2 4AA", result.getPostcode());
        assertEquals("PENDING", result.getStatus());
    }

    @Test
    void shouldThrowExceptionWhenDeliveryDoesNotExist() {

        // Arrange - tell the fake repository that ID 999 does not exist
        when(deliveryRepository.findById(999L))
                .thenReturn(Optional.empty());

        // Act and Assert
        DeliveryNotFoundException exception = assertThrows(
                DeliveryNotFoundException.class,
                () -> deliveryController.getDeliveryById(999L)
        );

        // Check the error message
        assertEquals(
                "Delivery with ID 999 was not found",
                exception.getMessage()
        );
    }

    @Test
    void shouldCreateDelivery() {

        // Arrange - delivery sent to the controller
        Delivery newDelivery = new Delivery(
                "Eva Smith",
                "20 Market Street",
                "M1 1AA",
                "PENDING"
        );

        // Delivery returned after it has been saved
        Delivery savedDelivery = new Delivery(
                "Eva Smith",
                "20 Market Street",
                "M1 1AA",
                "PENDING"
        );

        savedDelivery.setId(2L);

        // Tell the fake repository what to return when save() is called
        when(deliveryRepository.save(newDelivery))
                .thenReturn(savedDelivery);

        // Act
        Delivery result =
                deliveryController.createDelivery(newDelivery);

        // Assert
        assertEquals(2L, result.getId());
        assertEquals("Eva Smith", result.getCustomerName());
        assertEquals("20 Market Street", result.getAddress());
        assertEquals("M1 1AA", result.getPostcode());
        assertEquals("PENDING", result.getStatus());
    }

    @Test
    void shouldUpdateDelivery() {

        // Arrange - delivery already stored
        Delivery existingDelivery = new Delivery(
                "John Smith",
                "15 King Street",
                "M2 4AA",
                "PENDING"
        );

        existingDelivery.setId(1L);

        // New values that will replace the existing values
        Delivery updatedDelivery = new Delivery(
                "John Smith",
                "15 King Street",
                "M2 4AA",
                "DELIVERED"
        );

        // Fake repository finds the existing delivery
        when(deliveryRepository.findById(1L))
                .thenReturn(Optional.of(existingDelivery));

        // Fake repository returns the updated object when it is saved
        when(deliveryRepository.save(existingDelivery))
                .thenReturn(existingDelivery);

        // Act
        Delivery result =
                deliveryController.updateDelivery(
                        1L,
                        updatedDelivery
                );

        // Assert
        assertEquals(1L, result.getId());
        assertEquals("John Smith", result.getCustomerName());
        assertEquals("15 King Street", result.getAddress());
        assertEquals("M2 4AA", result.getPostcode());
        assertEquals("DELIVERED", result.getStatus());
    }

    @Test
    void shouldDeleteDeliveryWhenIdExists() {

        // Arrange - tell the fake repository that ID 1 exists
        when(deliveryRepository.existsById(1L))
                .thenReturn(true);

        // Act
        String result =
                deliveryController.deleteDelivery(1L);

        // Assert - check returned message
        assertEquals(
                "Delivery deleted successfully",
                result
        );

        // Check that the controller actually asked the repository to delete ID 1
        verify(deliveryRepository).deleteById(1L);
    }
    @Test
    void shouldThrowExceptionWhenDeletingMissingDelivery() {

        // Arrange - tell the fake repository that ID 999 does not exist
        when(deliveryRepository.existsById(999L))
                .thenReturn(false);

        // Act and Assert - deleting ID 999 should throw our custom exception
        DeliveryNotFoundException exception = assertThrows(
                DeliveryNotFoundException.class,
                () -> deliveryController.deleteDelivery(999L)
        );

        // Check the error message
        assertEquals(
                "Delivery with ID 999 was not found",
                exception.getMessage()
        );
    }
}