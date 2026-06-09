package com.example.demo.controller;

import com.example.demo.domain.RoomType;
import com.example.demo.dto.RoomTypeRequest;
import com.example.demo.dto.RoomTypeResponse;
import com.example.demo.service.RoomTypeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/roomType")
public class RoomTypeController {
    private final RoomTypeService roomTypeService;

    public RoomTypeController(RoomTypeService roomTypeService) {this.roomTypeService = roomTypeService;}

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RoomTypeResponse createRoomType(@Valid @RequestBody RoomTypeRequest roomTypeRequest) {
        return roomTypeService.createRoomType(roomTypeRequest);
    }

    @GetMapping
    public List<RoomTypeResponse> getAllRoomTypes() {
        return roomTypeService.getAllRoomTypes();
    }

    @GetMapping("/byName/{name}")
    public RoomTypeResponse getRoomTypeByName(@PathVariable String name) {
        return roomTypeService.getRoomTypeByName(name);
    }

    @PatchMapping("updateRoomType/{id}")
    public RoomTypeResponse updateRoomType(@PathVariable Integer id, @Valid @RequestBody RoomTypeRequest roomTypeRequest) {
        return roomTypeService.updateRoomType(id, roomTypeRequest);
    }

    @DeleteMapping("{id}")
    public void deleteRoomType(@PathVariable Integer id) {
        roomTypeService.deleteRoomType(id);
    }
}
