package com.app.advaitassignment.serviceImpl;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.app.advaitassignment.services.TokenBlacklist;

@Service
public class InMemoryTokenBlacklist implements TokenBlacklist {
    private Set<String> blacklist = new HashSet<>();

    @Override
    public void addToBlacklist(String token) {
        blacklist.add(token);
        System.out.println("token stored "+token);
    }

    @Override
    public boolean isBlacklisted(String token) {
        return blacklist.contains(token);
    }
}
