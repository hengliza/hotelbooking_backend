package com.example.demo.service;

import com.example.demo.dto.EventRequest;
import com.example.demo.dto.EventResponse;

import java.util.List;

public interface EventService {
    EventResponse create(EventRequest eventRequest);
    List<EventResponse> getAll();
    EventResponse getById(Integer id);
    EventResponse update(Integer id,EventRequest eventRequest);
    void delete(Integer eventId);
}
