package com.app.advaitassignment.services;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class TokenBlacklistService {
    
    public String extractTokenFromRequest(HttpServletRequest request) {
    String authorizationHeader = request.getHeader("Authorization");

    if (StringUtils.hasText(authorizationHeader) && authorizationHeader.startsWith("Bearer ")) {
        return authorizationHeader.substring(7);
    }

    // If the Authorization header is not valid, return null
    return "Token is not valid";
}
}
