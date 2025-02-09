package com.example.multiple_choice_exam.models;

import com.example.multiple_choice_exam.enums.Gender;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

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
    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime createAt;
    @Column(nullable = false)
    @UpdateTimestamp
    private LocalDateTime updateAt;
    @Column(nullable = false)
    private boolean deleted = false;
    @ManyToMany
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles;
}
