package com.example.demo.domain;

import lombok.Data;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Entity
@Data
@NoArgsConstructor
@Table(name = "reservations")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "checkin_date",nullable = false)
    private Timestamp checkinDate;

    @Column(name = "checkout_date",nullable = false)
    private Timestamp checkoutDate;

    @Column(name = "adult_amount", nullable = false)
    private Integer adultAmount;

    @Column(name = "child_amount")
    private Integer childAmount;

    @Column(nullable = false, length = 20)
    private String status;

    @Column(name = "created_date",nullable = false)
    private Timestamp createdDate;

    @Column(name = "total_price", nullable = false)
    private Double totalPrice;

    @ManyToOne
    @JoinColumn(name = "room_id", nullable = false, referencedColumnName = "id")
    private Room room;

    @OneToOne(mappedBy = "reservation", cascade = CascadeType.ALL)
    private Receipt receipt;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false, referencedColumnName = "id")
    private User user;
}
