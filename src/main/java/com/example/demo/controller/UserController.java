package com.example.demo.controller;

import com.example.demo.dto.UserRequest;
import com.example.demo.dto.UserResponse;
import com.example.demo.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PatchMapping("/updateUser/{id}")
    public UserResponse updateUser(@PathVariable Integer id,@Valid @RequestBody UserRequest userRequest) {
        return userService.updateUser(id,userRequest);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable Integer id) {
        userService.deleteUser(id);
    }
}
