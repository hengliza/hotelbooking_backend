package com.example.demo.repository;

import com.example.demo.domain.Receipt;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReceiptRepository extends JpaRepository<Receipt, Integer> {
    Optional<Receipt> findByBookingNumber(String bookingNumber);
    Optional<Receipt> findByReservationId(Integer reservationId);

}
