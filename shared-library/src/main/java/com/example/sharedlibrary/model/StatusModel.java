package com.example.sharedlibrary.model;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public class StatusModel {
    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime createAt;
    @Column(nullable = false)
    @UpdateTimestamp
    private LocalDateTime updateAt;
    @Column(nullable = false)
    private boolean deleted = false;
}
