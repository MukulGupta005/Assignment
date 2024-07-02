package com.app.advaitassignment.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.advaitassignment.dtos.LoginUserDto;
import com.app.advaitassignment.dtos.RegisterUserDto;
import com.app.advaitassignment.entities.User;
import com.app.advaitassignment.response.LoginResponse;
import com.app.advaitassignment.response.LogoutResponse;
import com.app.advaitassignment.services.AuthenticationService;
import com.app.advaitassignment.services.JwtService;
import com.app.advaitassignment.services.TokenBlacklist;
import com.app.advaitassignment.services.TokenBlacklistService;
import org.springframework.security.core.Authentication;

import jakarta.servlet.http.HttpServletRequest;

@RequestMapping("/auth")
@RestController
public class AuthenticationController {
    private final JwtService jwtService;
    
    private final AuthenticationService authenticationService;

    @Autowired
    private TokenBlacklistService tokenBlacklistService;

    @Autowired
    private TokenBlacklist tokenBlacklist;



    public AuthenticationController(JwtService jwtService, AuthenticationService authenticationService) {
        this.jwtService = jwtService;
        this.authenticationService = authenticationService;
    }

    @PostMapping("/signup")
    public ResponseEntity<User> register(@RequestBody RegisterUserDto registerUserDto) {
        User registeredUser = authenticationService.signup(registerUserDto);

        return ResponseEntity.ok(registeredUser);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticate(@RequestBody LoginUserDto loginUserDto) {
        User authenticatedUser = authenticationService.authenticate(loginUserDto);

        String jwtToken = jwtService.generateToken(authenticatedUser);

        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setToken(jwtToken);
        loginResponse.setExpiresIn(jwtService.getExpirationTime());

        return ResponseEntity.ok(loginResponse);
    }
    @PostMapping("/logout")
public ResponseEntity<LogoutResponse> logout(HttpServletRequest request) {
    String token = tokenBlacklistService.extractTokenFromRequest(request);

    if (token == null) {
        return ResponseEntity.badRequest().body(new LogoutResponse("Invalid token", null));
    }

    tokenBlacklist.addToBlacklist(token);

    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    if (authentication != null && authentication.getPrincipal() instanceof User) {
        User currentUser = (User) authentication.getPrincipal();
        String username = currentUser.getUsername();


        return ResponseEntity.ok(new LogoutResponse("User logged out successfully", username));
    } else {
        return ResponseEntity.badRequest().body(new LogoutResponse("Unable to retrieve current user", null));
    }
}

}
