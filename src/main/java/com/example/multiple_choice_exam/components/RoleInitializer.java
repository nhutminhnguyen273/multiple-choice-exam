package com.example.multiple_choice_exam.components;

import com.example.multiple_choice_exam.models.Role;
import com.example.multiple_choice_exam.repositories.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RoleInitializer implements CommandLineRunner {
    private final RoleRepository roleRepository;


    @Override
    public void run(String... args) throws Exception {
        roleRepository.findByName("ADMIN").orElseGet(
                () -> {
                    Role admin = new Role();
                    admin.setName("ADMIN");
                    return roleRepository.save(admin);
                }
        );
        roleRepository.findByName("TEACHER").orElseGet(
                () -> {
                    Role teacher = new Role();
                    teacher.setName("TEACHER");
                    return roleRepository.save(teacher);
                }
        );
        roleRepository.findByName("STUDENT").orElseGet(
                () -> {
                    Role student = new Role();
                    student.setName("STUDENT");
                    return roleRepository.save(student);
                }
        );
    }
}
