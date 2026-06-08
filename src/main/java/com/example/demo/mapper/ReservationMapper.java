package com.example.demo.mapper;

import com.example.demo.domain.Reservation;
import com.example.demo.domain.Room;
import com.example.demo.domain.User;
import com.example.demo.dto.ReservationRequest;
import com.example.demo.dto.ReservationResponse;
import java.sql.Timestamp;

public class ReservationMapper {

    public static ReservationResponse toResponse(Reservation reservation) {
        if (reservation == null) return null;
        return new ReservationResponse(
                reservation.getCheckinDate(),
                reservation.getCheckoutDate(),
                reservation.getAdultAmount(),
                reservation.getChildAmount(),
                reservation.getStatus(),
                reservation.getCreatedDate(),
                RoomMapper.toResponse(reservation.getRoom())
        );
    }

    public static Reservation toEntity(ReservationRequest request, Room room, User user) {
        if (request == null) return null;
        Reservation reservation = new Reservation();
        reservation.setCheckinDate(request.checkinDate());
        reservation.setCheckoutDate(request.checkoutDate());
        reservation.setAdultAmount(request.adultAmount());
        reservation.setChildAmount(request.childAmount());
        reservation.setRoom(room);
        reservation.setUser(user);
        reservation.setStatus("PENDING"); // Default business status setup
        reservation.setCreatedDate(new Timestamp(System.currentTimeMillis()));
        return reservation;
    }
}