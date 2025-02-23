package com.example.userservice.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AuthResponse {
    private int statusCode;
    private String message;
    private String token;

    public AuthResponse(String message, String token) {
        this.statusCode = 200;
        this.message = message;
        this.token = token;
    }

    public AuthResponse(int statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }
}
