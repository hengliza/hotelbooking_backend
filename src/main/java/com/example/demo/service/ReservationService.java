package com.example.demo.service;

import com.example.demo.dto.ReservationRequest;
import com.example.demo.dto.ReservationResponse;

import java.util.List;

public interface ReservationService {
    ReservationResponse createReservation(ReservationRequest reservationRequest);
    List<ReservationResponse> getAllReservations();
    List<ReservationResponse> getReservationsByUserId(Integer userId);
    ReservationResponse updateReservationStatus(Integer id, String status);
    void cancelReservation(Integer id);
}
