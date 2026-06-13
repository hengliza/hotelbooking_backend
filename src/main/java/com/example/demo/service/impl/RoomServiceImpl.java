package com.example.demo.service.impl;

import com.example.demo.domain.Room;
import com.example.demo.domain.RoomType;
import com.example.demo.dto.RoomRequest;
import com.example.demo.dto.RoomResponse;
import com.example.demo.dto.RoomTypeRequest;
import com.example.demo.mapper.RoomMapper;
import com.example.demo.repository.RoomRepository;
import com.example.demo.repository.RoomTypeRepository;
import com.example.demo.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {
    private final RoomRepository roomRepository;
    private final RoomTypeRepository roomTypeRepository;
    private final RoomMapper roomMapper;

    @Override
    public RoomResponse createRoom(RoomRequest roomRequest) {
        if(roomRepository.findByRoomNumber(roomRequest.roomNumber()).isPresent()){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Room number already exists");
        }

        RoomType roomType = roomTypeRepository.findById(roomRequest.roomTypeId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST,"Room Type not found with id: " + roomRequest.roomTypeId()));
        Room room= roomMapper.toEntity(roomRequest, roomType);

        return roomMapper.toResponse(roomRepository.save(room));
    }

    @Override
    public List<RoomResponse> getAllRooms() {
        List<Room> rooms = roomRepository.findAll();
        return rooms.stream().map(roomMapper::toResponse).collect(Collectors.toList());
    }

    @Override
    public RoomResponse getRoomByNumber(String number) {
        Room room = roomRepository.findByRoomNumber(number).orElseThrow(() -> new ResponseStatusException(HttpStatus.NO_CONTENT, "Room not found"));

        return roomMapper.toResponse(room);
    }

    @Override
    public RoomResponse updateRoom(Integer id, RoomRequest roomRequest) {
        Room existingRoom = roomRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NO_CONTENT, "Room not found with id: " + id));

        RoomType roomType = roomTypeRepository.findById(roomRequest.roomTypeId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NO_CONTENT, "Room Type not found with id: " + roomRequest.roomTypeId()));

        existingRoom.setRoomNumber(roomRequest.roomNumber());
        existingRoom.setRoomType(roomType);

        if (roomRequest.status() != null) {
            existingRoom.setStatus(roomRequest.status());
        }

        Room updatedRoom = roomRepository.save(existingRoom);
        return roomMapper.toResponse(updatedRoom);
    }

    @Override
    public void deleteRoom(String number) {
        Room existingRoom = roomRepository.findByRoomNumber(number)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NO_CONTENT, "Room not found with id: " + number));
        roomRepository.delete(existingRoom);
    }
}
