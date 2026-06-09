package com.example.demo.service.impl;

import com.example.demo.domain.User;
import com.example.demo.dto.UserRequest;
import com.example.demo.dto.UserResponse;
import com.example.demo.mapper.UserMapper;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImplement implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public UserResponse updateUser(Integer id, UserRequest userRequest) {
        User existedUser = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found with this id"+ id));

        existedUser.setUsername(userRequest.username());
        existedUser.setEmail(userRequest.email());
        existedUser.setPhoneNumber(userRequest.phoneNumber());
        User updatedUser = userRepository.save(existedUser);

        return userMapper.toResponse(updatedUser);
    }

    @Override
    public void deleteUser(Integer id) {
        if (!userRepository.existsById(id)) {
            throw new RuntimeException("User not found with this id" +id);
        }
        userRepository.deleteById(id);
    }

    @Override
    public UserResponse getUserById(Integer id) {
        User existedUser = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found with this id"+ id));

        return userMapper.toResponse(existedUser);
    }
}
