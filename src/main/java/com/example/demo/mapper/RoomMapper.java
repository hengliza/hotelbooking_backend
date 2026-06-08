package com.example.demo.mapper;

import com.example.demo.domain.Room;
import com.example.demo.domain.RoomType;
import com.example.demo.dto.RoomRequest;
import com.example.demo.dto.RoomResponse;

public class RoomMapper {

    public static RoomResponse toResponse(Room room) {
        if (room == null) return null;
        return new RoomResponse(
                room.getRoomNumber(),
                RoomTypeMapper.toResponse(room.getRoomType()), // Safe nested conversion
                room.getStatus()
        );
    }

    public static Room toEntity(RoomRequest request, RoomType roomType) {
        if (request == null) return null;
        Room room = new Room();
        room.setRoomNumber(request.roomNumber());
        room.setRoomType(roomType);
        room.setStatus("AVAILABLE");
        return room;
    }
}