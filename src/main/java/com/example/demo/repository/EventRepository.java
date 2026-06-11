package com.example.demo.repository;

import com.example.demo.domain.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EventRepository extends JpaRepository<Event, Integer> {
    Optional<Event> findById(Integer id);
    Boolean existsByTitle(String title);
}
