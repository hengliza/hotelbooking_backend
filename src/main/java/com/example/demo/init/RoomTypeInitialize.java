//package com.example.demo.init;
//
//import com.example.demo.domain.RoomType;
//import com.example.demo.repository.RoomTypeRepository;
//import jakarta.annotation.PostConstruct;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Component;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Component
//@RequiredArgsConstructor
//public class RoomTypeInitialize {
//    private final RoomTypeRepository roomTypeRepository;
//
//    @PostConstruct
//    public void init() {
//        if (roomTypeRepository.count() == 0) {
//            RoomType kingRoom = new RoomType();
//            kingRoom.setName("King Room");
//
//            RoomType queenRoom = new RoomType();
//            queenRoom.setName("Queen Room");
//
//            RoomType familyRoom = new RoomType();
//            familyRoom.setName("Family Room");
//
//            RoomType sweetRoom = new RoomType();
//            sweetRoom.setName("Sweet Room");
//
//            RoomType regularRoom = new RoomType();
//            regularRoom.setName("Regular Room");
//
//            roomTypeRepository.saveAll(List.of(kingRoom, queenRoom, familyRoom, sweetRoom, regularRoom));
//        }
//    }
//}
