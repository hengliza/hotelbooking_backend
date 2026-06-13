package com.example.demo.service;

import com.example.demo.domain.RefreshToken;
import com.example.demo.domain.User;

public interface RefreshService {
    String createRefreshToken(User user);
    RefreshToken validateRefreshToken(String token);
    void deleteByUser(User user);
}
