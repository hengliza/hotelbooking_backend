package com.example.demo.service;

import com.example.demo.domain.RoomType;
import com.example.demo.dto.RoomTypeRequest;
import com.example.demo.dto.RoomTypeResponse;

import java.util.List;

public interface RoomTypeService {
    RoomType createRoomType(RoomTypeRequest roomTypeRequest);
    List<RoomTypeResponse> getAllRoomTypes();
    RoomTypeResponse getRoomTypeById(Integer id);
    RoomTypeResponse getRoomTypeByName(String name);
    RoomTypeResponse updateRoomType(Integer id, RoomTypeRequest roomTypeRequest);
    void deleteRoomType(Integer id);
}
