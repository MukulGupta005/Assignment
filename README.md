
---

# AdvaitAssignment API Documentation
Demo: https://youtu.be/mWvqt6PcDLk

This document outlines the API endpoints for user authentication and management within the AdvaitAssignment project. The API is built using Spring Boot and leverages Spring Security for authentication and authorization.

## Base URL

All API requests should be sent to `http://localhost:8005`.

## AuthenticationController

### Signup

**Endpoint:** `POST /auth/signup`

**Description:** Registers a new user with the provided details.

**Request Body:**
```json
{
  "fullName": "Ravindra Gupta",
  "email": "Ravindra@gmail.com",
  "password": "Ravindra@124"
}
```

**Response:**
```json
{
  "id": 652,
  "fullName": "Ravindran Gupta",
  "email": "Ravindran@gmail.com",
  "password": "$2a$10$XVOp8v0IrhDd8Juwxl2zcO53GeBbIzv4/c8cIj2SjLPMrJwI.SgQi",
  "createdAt": "2024-07-02T09:31:43.738+00:00",
  "updatedAt": "2024-07-02T09:31:43.738+00:00",
  "enabled": true,
  "authorities": [],
  "username": "Ravindran@gmail.com",
  "credentialsNonExpired": true,
  "accountNonExpired": true,
  "accountNonLocked": true
}
```

### Login

**Endpoint:** `POST /auth/login`

**Description:** Authenticates a user and returns a JWT token upon successful authentication.

**Request Body:**
```json
{
  "email": "example@example.com",
  "password": "your_password"
}
```

**Response:**
```json
{
  "token": "eyJhbGciOiJIUzI1NiJ9...",
  "expiresIn": "3600000"
}
```

### Logout (Token Revoke Functionality)

**Endpoint:** `POST /auth/logout`

**Description:** Logs out the authenticated user by invalidating their JWT token.

**Headers:**
- Authorization: Bearer {token}

**Response:**
```json
{
  "message": "User logged out successfully",
  "username": "example@example.com"
}
```

## UserController

### Get Current User

**Endpoint:** `GET /users/me`

**Description:** Returns the details of the currently authenticated user.

**Headers:**
- Authorization: Bearer {token}

**Response:**
```json
{
  "id": 123,
  "fullName": "John Doe",
  "email": "john.doe@example.com",
 ...
}
```

### Get All Users

**Endpoint:** `GET /users/`

**Description:** Lists all users in the system.

**Headers:**
- Authorization: Bearer {token}

**Response:**
```json
[
  {
    "id": 1,
    "fullName": "Jane Doe",
    "email": "jane.doe@example.com",
   ...
  },
 ...
]
```

---
