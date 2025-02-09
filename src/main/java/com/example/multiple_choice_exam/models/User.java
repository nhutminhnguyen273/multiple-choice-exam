package com.example.multiple_choice_exam.models;

import com.example.multiple_choice_exam.enums.Gender;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(length = 10, unique = true, nullable = false)
    private String code;
    @Column(length = 250, nullable = false)
    private String password;
    @Column(length = 150, nullable = false)
    private String firstname;
    @Column(length = 150, nullable = false)
    private String lastname;
    @Column(nullable = false)
    private LocalDate dateOfBirth;
    @Column(length = 10, nullable = false)
    @Enumerated(EnumType.STRING)
    private Gender gender;
    @Column(length = 150, unique = true, nullable = false)
    private String email;
    @Column(length = 10, unique = true, nullable = false)
    private String phone;
}
