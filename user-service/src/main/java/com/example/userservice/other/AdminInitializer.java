package com.example.userservice.other;

import com.example.userservice.enums.Gender;
import com.example.userservice.enums.Role;
import com.example.userservice.model.User;
import com.example.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class AdminInitializer implements CommandLineRunner {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        userRepository.findByRole(Role.ADMIN).orElseGet(
                () -> {
                    User user = new User();
                    user.setCode("ADMIN00954");
                    user.setFirstname("Nguyễn Văn");
                    user.setLastname("An");
                    user.setDateOfBirth(LocalDate.of(1997, 2, 3));
                    user.setGender(Gender.MALE);
                    user.setEmail("admin@gmail.com");
                    user.setPhone("0987654321");
                    user.setMajorCode("CNTT098765");
                    user.setPassword(passwordEncoder.encode("ADMIN00954"));
                    user.setRole(Role.ADMIN);
                    return userRepository.save(user);
                }
        );
    }
}
