package com.example.demo.service;

import com.example.demo.dto.UserRequest;
import com.example.demo.dto.UserResponse;

public interface UserService {
    UserResponse updateUser(UserRequest userRequest);
    void deleteUser(Integer id);
    UserResponse getUserById(Integer id);
}
