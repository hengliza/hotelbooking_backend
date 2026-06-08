package com.example.demo.service;

import com.example.demo.dto.EventRequest;
import com.example.demo.dto.EventResponse;

public interface EventService {
    EventResponse createEvent(EventRequest eventRequest);
    EventResponse updateEvent(EventRequest eventRequest);
    void deleteEvent(String eventId);
}
