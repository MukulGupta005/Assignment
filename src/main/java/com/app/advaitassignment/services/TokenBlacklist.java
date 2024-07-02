package com.app.advaitassignment.services;

public interface TokenBlacklist {
    void addToBlacklist(String token);
    boolean isBlacklisted(String token);
}


