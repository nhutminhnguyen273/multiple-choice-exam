package com.example.multiple_choice_exam.components;

import com.example.multiple_choice_exam.enums.Gender;
import com.example.multiple_choice_exam.models.Role;
import com.example.multiple_choice_exam.models.User;
import com.example.multiple_choice_exam.repositories.RoleRepository;
import com.example.multiple_choice_exam.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class AdminInitializer implements CommandLineRunner {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;


    @Override
    public void run(String... args) throws Exception {
        Role admin = roleRepository.findByName("ADMIN").orElseGet(
                () -> {
                    Role role = new Role();
                    role.setName("ADMIN");
                    return roleRepository.save(role);
                }
        );
        userRepository.findByEmail("admin@gmail.com").orElseGet(
                () -> {
                    User user = new User();
                    user.setCode("ADMIN12345");
                    user.setPassword(passwordEncoder.encode("ADMIN12345"));
                    user.setFirstname("Nguyễn Văn");
                    user.setLastname("An");
                    user.setDateOfBirth(LocalDate.of(1997, 2, 7));
                    user.setGender(Gender.MALE);
                    user.setEmail("admin@gmail.com");
                    user.setPhone("0987654321");
                    user.setRoles(new HashSet<>(Set.of(admin)));
                    return userRepository.save(user);
                }
        );
    }
}
