package com.example.demo.service.impl;

import com.example.demo.domain.Reservation;
import com.example.demo.domain.Room;
import com.example.demo.domain.User;
import com.example.demo.dto.ReceiptRequest;
import com.example.demo.dto.ReservationRequest;
import com.example.demo.dto.ReservationResponse;
import com.example.demo.mapper.ReservationMapper;
import com.example.demo.repository.ReceiptRepository;
import com.example.demo.repository.ReservationRepository;
import com.example.demo.repository.RoomRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.ReceiptService;
import com.example.demo.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService {
    private final ReservationRepository reservationRepository;
    private final RoomRepository roomRepository;
    private final UserRepository userRepository;
    private final ReservationMapper reservationMapper;
    private final ReceiptRepository receiptRepository;
    private final ReceiptService receiptService;

    @Override
    public ReservationResponse createReservation(ReservationRequest reservationRequest) {
        Room room = roomRepository.findById(reservationRequest.roomId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NO_CONTENT, "Room not found with id: " + reservationRequest.roomId()));

        if (!"AVAILABLE".equalsIgnoreCase(room.getStatus())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Room " + room.getRoomNumber() + " is currently occupied or under maintenance.");
        }

        User user = userRepository.findById(reservationRequest.userId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NO_CONTENT, "User not found with id: " + reservationRequest.userId()));

        Reservation reservation = reservationMapper.toEntity(reservationRequest, room, user);

        room.setStatus("OCCUPIED");
        roomRepository.save(room);

        Reservation savedReservation = reservationRepository.save(reservation);
        ReceiptRequest receiptRequest = new ReceiptRequest(savedReservation.getId());
        receiptService.createReceipt(receiptRequest);

        return reservationMapper.toResponse(savedReservation);
    }

    @Override
    public List<ReservationResponse> getAllReservations() {
        List<Reservation> reservations = reservationRepository.findAll();
        return reservations.stream().map(reservationMapper::toResponse).collect(Collectors.toList());
    }

    @Override
    public List<ReservationResponse> getReservationsByUserId(Integer userId) {
        List<Reservation> reservations = reservationRepository.findByUserId(userId);
        return reservations.stream().map(reservationMapper::toResponse).collect(Collectors.toList());
    }

    @Override
    public ReservationResponse updateReservationStatus(Integer id, String status) {
        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NO_CONTENT, "Reservation record not found with id: " + id));

        reservation.setStatus(status.toUpperCase());

        if ("CANCELLED".equalsIgnoreCase(status) || "COMPLETED".equalsIgnoreCase(status)) {
            Room room = reservation.getRoom();
            if (room != null) {
                room.setStatus("AVAILABLE");
                roomRepository.save(room);
            }
        }

        Reservation updatedReservation = reservationRepository.save(reservation);
        return reservationMapper.toResponse(updatedReservation);
    }

    @Override
    public void cancelReservation(Integer id) {
        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NO_CONTENT, "Reservation not found"));

        reservation.setStatus("CANCELLED");

        if (reservation.getRoom() != null) {
            reservation.getRoom().setStatus("AVAILABLE");
        }

        receiptRepository.findByReservationId(reservation.getId())
                .ifPresent(receipt -> {
                    receipt.setStatus("VOIDED");
                    receiptRepository.save(receipt);
                });

        reservationRepository.save(reservation);
    }
}
