package com.example.demo.service;

import com.example.demo.dto.UserRequest;
import com.example.demo.dto.UserResponse;

import java.util.List;

public interface UserService {
    UserResponse updateUser(Integer id,UserRequest userRequest);
    void deleteUser(Integer id);
    UserResponse getUserById(Integer id);
    List<UserResponse> getAllUsers();
}
