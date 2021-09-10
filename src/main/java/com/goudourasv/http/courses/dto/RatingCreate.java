package com.goudourasv.http.courses.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public class RatingCreate {
    private int userRating;
    private String message;
    @JsonProperty("user")
    private UUID userId;

    public RatingCreate(int userRating, String message, UUID userId) {
        this.userRating = userRating;
        this.message = message;
        this.userId = userId;
    }

    public RatingCreate() {
    }

    public int getUserRating() {
        return userRating;
    }

    public String getMessage() {
        return message;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserRating(int userRating) {
        this.userRating = userRating;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }
}
