package com.example.userservice.service;

import com.example.sharedlibrary.exception.custom.NotFoundException;
import com.example.userservice.dto.auth_dto.LoginDTO;
import com.example.userservice.model.User;
import com.example.userservice.repository.UserRepository;
import com.example.userservice.response.AuthResponse;
import com.example.userservice.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthResponse login(LoginDTO input) {
        try {
            User user = userRepository.findByCode(input.getCode()).orElseThrow(
                    () -> new NotFoundException("Mã không chính xác")
            );
            if (!passwordEncoder.matches(input.getPassword(), user.getPassword()))
                return new AuthResponse(
                        400,
                        "Mật khẩu không chính xác"
                );
            String accessToken = jwtUtil.generateAccessToken(user.getId());
            String refreshToken = jwtUtil.generateRefreshToken(user.getId());
            return new AuthResponse(
                    "Success",
                    accessToken
            );
        } catch (NotFoundException ex) {
            return new AuthResponse(
                    404,
                    ex.getMessage()
            );
        } catch (Exception ex) {
            return new AuthResponse(
                    500,
                    "Internal server error: " + ex.getMessage()
            );
        }
    }
}
