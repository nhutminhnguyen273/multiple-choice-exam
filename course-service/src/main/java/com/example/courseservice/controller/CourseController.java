package com.example.courseservice.controller;

import com.example.courseservice.dto.CourseDTO;
import com.example.courseservice.dto.CreateCourseDTO;
import com.example.courseservice.dto.UpdateCourseDTO;
import com.example.courseservice.model.Course;
import com.example.courseservice.service.CourseService;
import com.example.sharedlibrary.response.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CourseController {
    private final CourseService courseService;

    @GetMapping("/")
    public Response<List<CourseDTO>> getAllCourses() {
        return courseService.getAllCourses();
    }

    @GetMapping("/{courseId}")
    public Response<CourseDTO> findCourseById(@PathVariable("courseId") String courseId) {
        return courseService.findCourseById(courseId);
    }

    @PostMapping("/")
    public Response<CourseDTO> createCourse(@RequestBody CreateCourseDTO input) {
        return courseService.createCourse(input);
    }

    @PutMapping("/{courseId}")
    public Response<CourseDTO> updateCourse(
            @PathVariable("courseId") String courseId, @RequestBody UpdateCourseDTO input) {
        return courseService.updateCourse(courseId, input);
    }

    @DeleteMapping("/{courseId}")
    public Response<Void> deleteCourse(@PathVariable("courseId") String courseId) {
        return courseService.deleteCourse(courseId);
    }
}
