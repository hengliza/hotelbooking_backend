package com.example.demo.service;

import com.example.demo.dto.ReviewRequest;
import com.example.demo.dto.ReviewResponse;

import java.util.List;

public interface ReviewService {
    ReviewResponse create(ReviewRequest reviewRequest);
    List<ReviewResponse> getReviewByRoomTypeId(Integer roomTypeId);
    List<ReviewResponse> getReviewByUserId(Integer userId);
    List<ReviewResponse> getAllReviews();
    ReviewResponse updateReview(Integer id,ReviewRequest reviewRequest);
    void deleteReview(Integer reviewId);
}
