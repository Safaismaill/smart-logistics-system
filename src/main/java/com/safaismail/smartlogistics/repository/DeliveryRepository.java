package com.safaismail.smartlogistics.repository;

import com.safaismail.smartlogistics.model.Delivery;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DeliveryRepository extends JpaRepository<Delivery, Long> {

    List<Delivery> findByStatusIgnoreCase(String status);

    List<Delivery> findByPostcodeContainingIgnoreCase(String postcode);

    List<Delivery> findByCustomerNameContainingIgnoreCase(String customerName);

    // Searches deliveries using any combination of filters
    // Searches deliveries using any combination of filters
    @Query("""

            SELECT d FROM Delivery d
        WHERE (:status = '' OR LOWER(d.status) = LOWER(:status))
        AND (:postcode = '' OR LOWER(d.postcode) LIKE CONCAT('%', CONCAT(LOWER(:postcode), '%')))
        AND (:customerName = '' OR LOWER(d.customerName) LIKE CONCAT('%', CONCAT(LOWER(:customerName), '%')))
        """)
    List<Delivery> searchDeliveries(
            @Param("status") String status,
            @Param("postcode") String postcode,
            @Param("customerName") String customerName);
    }