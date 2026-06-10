package com.example.demo.controller;

import com.example.demo.dto.ReviewRequest;
import com.example.demo.dto.ReviewResponse;
import com.example.demo.service.ReviewService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ReviewResponse create(@Valid @RequestBody ReviewRequest request) {
        return reviewService.create(request);
    }

    @GetMapping
    public List<ReviewResponse> getAllReviews() {
        return reviewService.getAllReviews();
    }

    @GetMapping("/room-type/{roomTypeId}")
    public List<ReviewResponse> getByRoomTypeId(@PathVariable Integer roomTypeId) {
        return reviewService.getReviewByRoomTypeId(roomTypeId);
    }


    @GetMapping("/user/{userId}")
    public List<ReviewResponse> getByUserId(@PathVariable Integer userId) {
        return reviewService.getReviewByUserId(userId);
    }

    @PatchMapping("/update/{id}")
    public ReviewResponse update(@PathVariable Integer id, @RequestBody ReviewRequest request) {
        return reviewService.updateReview(id,request);
    }

    @DeleteMapping("/{reviewId}")
    public void delete(@PathVariable Integer reviewId) {
        reviewService.deleteReview(reviewId);
    }
}
