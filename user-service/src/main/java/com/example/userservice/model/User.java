package com.example.userservice.model;

import com.example.sharedlibrary.model.StatusModel;
import com.example.userservice.enums.Gender;
import com.example.userservice.enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User extends StatusModel {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(length = 10, unique = true, nullable = false)
    private String code;
    @Column(length = 150, nullable = false)
    private String firstname;
    @Column(length = 150, nullable = false)
    private String lastname;
    @Column(nullable = false)
    private LocalDate dateOfBirth;
    @Column(length = 50, nullable = false)
    @Enumerated(EnumType.STRING)
    private Gender gender;
    @Column(length = 150, nullable = false, unique = true)
    private String email;
    @Column(length = 10, nullable = false, unique = true)
    private String phone;
    @Column(length = 10, nullable = false)
    private String majorCode;
    @Column(length = 250, nullable = false)
    private String password;
    @Column(length = 50, nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role = Role.STUDENT;
}
