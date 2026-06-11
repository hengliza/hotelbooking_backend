package com.example.demo.service.impl;

import com.example.demo.domain.Event;
import com.example.demo.dto.EventRequest;
import com.example.demo.dto.EventResponse;
import com.example.demo.mapper.EventMapper;
import com.example.demo.repository.EventRepository;
import com.example.demo.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;
    private final EventMapper eventMapper;

    @Override
    public EventResponse create(EventRequest request) {
        if (eventRepository.existsByTitle(request.title())){
            throw new ResponseStatusException(HttpStatus.CONFLICT,"An event with this title already exists.");
        }

        if (request.startDate().isBefore(LocalDateTime.now())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Event date cannot be set in the past.");
        }
        if (request.endDate().isBefore(LocalDateTime.now())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Event date cannot be set in the past.");
        }

        Event event = eventMapper.toEntity(request);

        return eventMapper.toResponse(
                eventRepository.save(event)
        );
    }

    @Override
    public List<EventResponse> getAll() {
        return eventRepository.findAll()
                .stream()
                .map(eventMapper::toResponse)
                .toList();
    }

    @Override
    public EventResponse getById(Integer id) {

        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NO_CONTENT,"Event not found"));

        return eventMapper.toResponse(event);
    }

    @Override
    public EventResponse update(Integer id, EventRequest request) {

        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NO_CONTENT,"Event not found"));

        event.setTitle(request.title());
        event.setDescription(request.description());
        event.setStartDate(request.startDate());
        event.setEndDate(request.endDate());

        return eventMapper.toResponse(
                eventRepository.save(event)
        );
    }

    @Override
    public void delete(Integer id) {
        eventRepository.deleteById(id);
    }
}