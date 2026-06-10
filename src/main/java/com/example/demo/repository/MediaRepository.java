package com.example.demo.repository;
import com.example.demo.domain.Media;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MediaRepository extends JpaRepository<Media, Integer> {

    Optional<Media> findByNameAndExtension(String name, String extension);
}
