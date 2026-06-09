package com.example.demo.mapper;

import com.example.demo.domain.Room;
import com.example.demo.domain.RoomType;
import com.example.demo.dto.RoomRequest;
import com.example.demo.dto.RoomResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RoomMapper {
    private final RoomTypeMapper roomTypeMapper;

    public RoomResponse toResponse(Room room) {
        if (room == null) return null;

        return new RoomResponse(
                room.getRoomNumber(),
                roomTypeMapper.toResponse(room.getRoomType()),
                room.getStatus()
        );
    }

    public Room toEntity(RoomRequest request, RoomType roomType) {
        if (request == null) return null;

        Room room = new Room();
        room.setRoomNumber(request.roomNumber());
        room.setRoomType(roomType);
        room.setStatus("AVAILABLE");
        return room;
    }
}