package com.example.demo.service.impl;

import com.example.demo.domain.RoomType;
import com.example.demo.dto.RoomTypeRequest;
import com.example.demo.dto.RoomTypeResponse;
import com.example.demo.mapper.RoomTypeMapper;
import com.example.demo.repository.RoomTypeRepository;
import com.example.demo.service.RoomTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoomTypeServiceImpl implements RoomTypeService {
    private final RoomTypeRepository roomTypeRepository;
    private final RoomTypeMapper roomTypeMapper;

    @Override
    public RoomTypeResponse createRoomType(RoomTypeRequest roomTypeRequest) {
        if(roomTypeRepository.findByName(roomTypeRequest.name()).isPresent()){
            throw new RuntimeException ("A room type with this name already exists");
        }
        RoomType roomType = roomTypeMapper.toEntity(roomTypeRequest);

        return roomTypeMapper.toResponse(roomTypeRepository.save(roomType));
    }

    @Override
    public List<RoomTypeResponse> getAllRoomTypes() {
        List<RoomType> roomTypes = roomTypeRepository.findAll();
        return roomTypes.stream().map(roomTypeMapper::toResponse).collect(Collectors.toList());
    }

    @Override
    public RoomTypeResponse getRoomTypeByName(String name) {
        RoomType roomType = roomTypeRepository.findByName(name)
                .orElseThrow(() -> new RuntimeException("There is no room type with this name"));
        return roomTypeMapper.toResponse(roomType);
    }

    @Override
    public RoomTypeResponse updateRoomType(Integer id, RoomTypeRequest roomTypeRequest) {
        RoomType roomType = roomTypeRepository.findById(id).orElseThrow(() -> new RuntimeException("There is no room type with this id"));

        roomType.setName(roomTypeRequest.name());
        roomType.setDescription(roomTypeRequest.description());
        roomType.setImage(roomTypeRequest.image());
        roomType.setPrice(roomTypeRequest.price());
        roomType.setCapacity(roomTypeRequest.capacity());

        return roomTypeMapper.toResponse(roomTypeRepository.save(roomType));
    }

    @Override
    public void deleteRoomType(Integer id) {
        RoomType roomType = roomTypeRepository.findById(id).orElseThrow(() -> new RuntimeException("There is no room type with this id"));
        roomTypeRepository.delete(roomType);
    }
}
