package com.example.demo.repository;

import com.example.demo.domain.RoomType;
import com.example.demo.dto.RoomTypeResponse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoomTypeRepository extends JpaRepository<RoomType, Integer> {
    Optional<RoomType> findByName(String name);
}
