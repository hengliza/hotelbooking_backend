package com.example.demo.controller;

import com.example.demo.dto.ReservationRequest;
import com.example.demo.dto.ReservationResponse;
import com.example.demo.service.ReservationService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservation")
public class ReservationController {

    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ReservationResponse createReservation(@Valid @RequestBody ReservationRequest request) {
        return reservationService.createReservation(request);
    }

    @GetMapping("/user/{id}")
    public List<ReservationResponse> getReservationByUserId(@PathVariable Integer id) {
        return reservationService.getReservationsByUserId(id);
    }

    @GetMapping
    public List<ReservationResponse> getAllReservations() {
        return reservationService.getAllReservations();
    }

    @PatchMapping("/{id}/status")
    public ReservationResponse updateReservationStatus(@PathVariable Integer id, @RequestParam String status) {
        return reservationService.updateReservationStatus(id, status);
    }

    @DeleteMapping("/{id}/cancel")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void cancelReservation(@PathVariable Integer id) {
        reservationService.cancelReservation(id);
    }
}