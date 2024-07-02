package com.app.advaitassignment.response;


public class LogoutResponse {
    private String status;
    private String currentUser;

    public LogoutResponse(String status, String currentUser) {
        this.status = status;
        this.currentUser = currentUser;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(String currentUser) {
        this.currentUser = currentUser;
    }
}
