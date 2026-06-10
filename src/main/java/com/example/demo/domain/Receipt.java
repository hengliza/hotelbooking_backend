package com.example.demo.domain;
import lombok.Data;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Entity
@Data
@NoArgsConstructor
@Table(name = "receipts")
public class Receipt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "booking_number",nullable = false, unique = true, length = 50)
    private String bookingNumber;

    @Column(name = "issued_at",nullable = false)
    private Timestamp issuedAt;

    @OneToOne
    @JoinColumn(name = "reservation_id", nullable = false, unique = true)
    private Reservation reservation;

    @Column(name = "status", nullable = false, length = 20)
    private String status = "PAID";
}
