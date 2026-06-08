package com.example.demo.mapper;

import com.example.demo.domain.RoomType;
import com.example.demo.dto.RoomTypeRequest;
import com.example.demo.dto.RoomTypeResponse;

public class RoomTypeMapper {

    public static RoomTypeResponse toResponse(RoomType roomType) {
        if (roomType == null) return null;
        return new RoomTypeResponse(
                roomType.getName(),
                roomType.getDescription(),
                roomType.getPrice(),
                roomType.getCapacity(),
                roomType.getImage()
        );
    }

    public static RoomType toEntity(RoomTypeRequest request) {
        if (request == null) return null;
        RoomType roomType = new RoomType();
        roomType.setName(request.name());
        roomType.setDescription(request.description());
        roomType.setPrice(request.price());
        roomType.setCapacity(request.capacity());
        roomType.setImage(request.image());
        return roomType;
    }
}