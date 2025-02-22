package com.example.courseservice.repository;

import com.example.courseservice.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<Course, String> {
    boolean existsByName(String name);
    Optional<Course> findByName(String name);
}
