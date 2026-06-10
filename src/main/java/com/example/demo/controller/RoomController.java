package com.example.demo.controller;

import com.example.demo.domain.RoomType;
import com.example.demo.dto.RoomRequest;
import com.example.demo.dto.RoomResponse;
import com.example.demo.dto.RoomTypeRequest;
import com.example.demo.dto.RoomTypeResponse;
import com.example.demo.service.RoomService;
import com.example.demo.service.RoomTypeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/room")
public class RoomController {
    private final RoomService roomService;

    public RoomController(RoomService roomService) {this.roomService = roomService;}

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RoomResponse createRoom(@Valid @RequestBody RoomRequest roomRequest) {
        return roomService.createRoom(roomRequest);
    }

    @GetMapping
    public List<RoomResponse> getAllRoom() {
        return roomService.getAllRooms();
    }

    @GetMapping("/byNumber/{number}")
    public RoomResponse getRoomByNumber(@PathVariable String number) {
        return roomService.getRoomByNumber(number);
    }

    @PatchMapping("updateRoom/{id}")
    public RoomResponse updateRoomType(@PathVariable Integer id, @Valid @RequestBody RoomRequest roomRequest) {
        return roomService.updateRoom(id, roomRequest);
    }

    @DeleteMapping("/{roomNumber}")
    public void deleteRoom(@PathVariable String roomNumber) {
        roomService.deleteRoom(roomNumber);
    }
}
