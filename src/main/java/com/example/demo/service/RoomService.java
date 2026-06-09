package com.example.demo.service;

import com.example.demo.dto.RoomRequest;
import com.example.demo.dto.RoomResponse;

import java.util.List;

public interface RoomService {
    RoomResponse createRoom(RoomRequest roomRequest);
    List<RoomResponse> getAllRooms();
    RoomResponse getRoomByNumber(String number);
    RoomResponse updateRoom(Integer id,RoomRequest roomRequest);
    void deleteRoom(String number);
}
