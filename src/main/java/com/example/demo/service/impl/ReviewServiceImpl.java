package com.example.demo.service.impl;

import com.example.demo.domain.Review;
import com.example.demo.domain.RoomType;
import com.example.demo.domain.User;
import com.example.demo.dto.ReviewRequest;
import com.example.demo.dto.ReviewResponse;
import com.example.demo.mapper.ReviewMapper;
import com.example.demo.repository.ReviewRepository;
import com.example.demo.repository.RoomTypeRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final ReviewMapper reviewMapper;
    private final UserRepository userRepository;
    private final RoomTypeRepository roomTypeRepository;

    @Override
    public ReviewResponse create(ReviewRequest request) {

        User user = userRepository.findById(request.userId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        RoomType roomType = roomTypeRepository.findById(request.roomTypeId())
                .orElseThrow(() -> new RuntimeException("Room type not found"));

        Review review = reviewMapper.toEntity(
                request,
                user,
                roomType
        );

        review = reviewRepository.save(review);

        return reviewMapper.toResponse(review);
    }

    @Override
    public List<ReviewResponse> getReviewByRoomTypeId(Integer roomTypeId) {

        return reviewRepository.findByRoomTypeId(roomTypeId)
                .stream()
                .map(reviewMapper::toResponse)
                .toList();
    }

    @Override
    public List<ReviewResponse> getReviewByUserId(Integer userId) {

        return reviewRepository.findByUserId(userId)
                .stream()
                .map(reviewMapper::toResponse)
                .toList();
    }

    @Override
    public List<ReviewResponse> getAllReviews() {

        return reviewRepository.findAll()
                .stream()
                .map(reviewMapper::toResponse)
                .toList();
    }

    @Override
    public ReviewResponse updateReview(Integer id,ReviewRequest reviewRequest) {

        Review review = reviewRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Review not found"));

        review.setMessage(reviewRequest.message());

        return reviewMapper.toResponse(reviewRepository.save(review));
    }

    @Override
    public void deleteReview(Integer reviewId) {

        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() ->
                        new RuntimeException("Review not found"));

        reviewRepository.delete(review);
    }
}