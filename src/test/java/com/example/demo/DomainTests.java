package com.example.demo;

import com.example.demo.domain.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class DomainTests {

    @Test
    void testRoomCreationAndProperties() {
        Room room = new Room();
        room.setRoomNumber("101");
        room.setStatus("AVAILABLE");

        assertNotNull(room);
        assertEquals("101", room.getRoomNumber());
        assertEquals("AVAILABLE", room.getStatus());
    }

    @Test
    void testRoomTypeCreationAndProperties() {
        RoomType roomType = new RoomType();
        roomType.setName("Deluxe");
        roomType.setDescription("A very nice room");
        roomType.setPrice(150.0);

        assertNotNull(roomType);
        assertEquals("Deluxe", roomType.getName());
        assertEquals(150.0, roomType.getPrice());
    }

    @Test
    void testUserCreationAndProperties() {
        User user = new User();
        user.setUsername("testuser");
        user.setEmail("test@example.com");
        user.setAuthProvider("local");

        assertNotNull(user);
        assertEquals("testuser", user.getUsername());
        assertEquals("test@example.com", user.getEmail());
        assertEquals("local", user.getAuthProvider());
    }

    @Test
    void testCategoryCreationAndProperties() {
        Category category = new Category();
        category.setName("Food");
        category.setDescription("Food items");

        assertNotNull(category);
        assertEquals("Food", category.getName());
        assertEquals("Food items", category.getDescription());
    }

    @Test
    void testEventCreationAndProperties() {
        Event event = new Event();
        event.setTitle("Wedding");
        event.setDescription("Grand Hall");

        assertNotNull(event);
        assertEquals("Wedding", event.getTitle());
        assertEquals("Grand Hall", event.getDescription());
    }
}
