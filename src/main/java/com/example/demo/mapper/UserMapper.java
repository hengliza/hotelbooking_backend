package com.example.demo.mapper;

import com.example.demo.domain.User;
import com.example.demo.dto.UserRequest;
import com.example.demo.dto.UserResponse;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public static UserResponse toResponse(User user) {
        if (user == null) return null;
        return new UserResponse(
                user.getUsername(),
                user.getEmail(),
                user.getPhoneNumber()
        );
    }

    public static User toEntity(UserRequest request) {
        if (request == null) return null;
        User user = new User();
        user.setUsername(request.username());
        user.setEmail(request.email());
        user.setPhoneNumber(request.phoneNumber());
        user.setAuthProvider(request.authProvider());
        user.setPassword(request.password());
        return user;
    }
}