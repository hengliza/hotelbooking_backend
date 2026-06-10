package com.example.demo.controller;

import com.example.demo.dto.EventRequest;
import com.example.demo.dto.EventResponse;
import com.example.demo.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/events")
@RequiredArgsConstructor
public class EventController {

    private final EventService eventService;

    @PostMapping
    public EventResponse create(@RequestBody EventRequest request) {
        return eventService.create(request);
    }

    @GetMapping
    public List<EventResponse> getAll() {
        return eventService.getAll();
    }

    @GetMapping("/{id}")
    public EventResponse getById(@PathVariable Integer id) {
        return eventService.getById(id);
    }

    @PatchMapping("/{id}")
    public EventResponse update(@PathVariable Integer id, @RequestBody EventRequest request) {
        return eventService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        eventService.delete(id);
    }
}