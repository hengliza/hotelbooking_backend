package com.example.demo.service;

import com.example.demo.dto.ReviewRequest;
import com.example.demo.dto.ReviewResponse;

import java.util.List;

public interface ReviewService {
    ReviewResponse addReview(ReviewRequest reviewRequest);
    ReviewResponse getReviewByRoomTypeId(Integer roomTypeId);
    ReviewResponse getReviewByBookingId(Integer bookingId);
    ReviewResponse getReviewByUserId(Integer userId);
    List<ReviewResponse> getAllReviews();
    ReviewResponse updateReview(ReviewRequest reviewRequest);
    void deleteReview(Integer reviewId);
}
