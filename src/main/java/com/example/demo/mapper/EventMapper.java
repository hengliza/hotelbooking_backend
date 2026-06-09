package com.example.demo.mapper;

import com.example.demo.domain.Event;
import com.example.demo.dto.EventRequest;
import com.example.demo.dto.EventResponse;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
public class EventMapper {

    public EventResponse toResponse(Event event) {
        if (event == null) return null;
        return new EventResponse(
                event.getTitle(),
                event.getDescription(),
                event.getStartDate(),
                event.getEndDate(),
                event.getCreatedAt()
        );
    }

    public Event toEntity(EventRequest request) {
        if (request == null) return null;
        Event event = new Event();
        event.setTitle(request.title());
        event.setDescription(request.description());
        event.setStartDate(request.startDate());
        event.setEndDate(request.endDate());
        event.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        return event;
    }
}