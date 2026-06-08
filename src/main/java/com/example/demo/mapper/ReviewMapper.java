package com.example.demo.mapper;

import com.example.demo.domain.Review;
import com.example.demo.domain.RoomType;
import com.example.demo.domain.User;
import com.example.demo.dto.ReviewRequest;
import com.example.demo.dto.ReviewResponse;
import java.sql.Timestamp;

public class ReviewMapper {

    public static ReviewResponse toResponse(Review review) {
        if (review == null) return null;
        return new ReviewResponse(
                review.getMessage(),
                review.getCreatedDate(),
                review.getUser() != null ? review.getUser().getUsername() : "Anonymous",
                review.getRoomType() != null ? review.getRoomType().getName() : null
        );
    }

    public static Review toEntity(ReviewRequest request, User user, RoomType roomType) {
        if (request == null) return null;
        Review review = new Review();
        review.setMessage(request.message());
        review.setUser(user);
        review.setRoomType(roomType);
        review.setCreatedDate(new Timestamp(System.currentTimeMillis()));
        return review;
    }
}