package com.example.multiple_choice_exam.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(length = 50, unique = true, nullable = false)
    private String name;
    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime createAt;
    @Column(nullable = false)
    @UpdateTimestamp
    private LocalDateTime updateAt;
    @Column(nullable = false)
    private boolean deleted = false;
    @ManyToMany(mappedBy = "roles")
    @JsonIgnore
    private Set<User> users;
}
