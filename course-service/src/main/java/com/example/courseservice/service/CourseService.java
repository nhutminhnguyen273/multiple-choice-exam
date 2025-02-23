package com.example.courseservice.service;

import com.example.courseservice.dto.CourseDTO;
import com.example.courseservice.dto.CreateCourseDTO;
import com.example.courseservice.dto.UpdateCourseDTO;
import com.example.courseservice.mapper.CourseMapper;
import com.example.courseservice.model.Course;
import com.example.courseservice.repository.CourseRepository;
import com.example.sharedlibrary.exception.custom.AlreadyExistedException;
import com.example.sharedlibrary.exception.custom.NotFoundException;
import com.example.sharedlibrary.response.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseService {
    private final CourseRepository courseRepository;
    private final CourseMapper courseMapper;

    public Response<List<CourseDTO>> getAllCourses() {
        try {
            List<Course> courses = courseRepository.findAll();
            List<CourseDTO> courseDTOList = courses
                    .stream()
                    .map(courseMapper::toCourseDTO)
                    .toList();
            return new Response<>("Success", courseDTOList);
        } catch (Exception ex) {
            return new Response<>(
                    500,
                    ex.getMessage()
            );
        }
    }

    public Response<CourseDTO> findCourseById(String courseId) {
        try {
            Course course = courseRepository.findById(courseId).orElseThrow(
                    () -> new NotFoundException("Không tìm thấy môn học")
            );
            CourseDTO courseDTO = courseMapper.toCourseDTO(course);
            return new Response<>("Success", courseDTO);
        } catch (NotFoundException ex) {
            return new Response<>(
                    404,
                    ex.getMessage()
            );
        } catch (Exception ex) {
            return new Response<>(
                    500,
                    ex.getMessage()
            );
        }
    }

    public Response<CourseDTO> findCourseByName(String courseName) {
        try {
            Course course = courseRepository.findByName(courseName).orElseThrow(
                    () -> new NotFoundException("Không tìm thấy môn học")
            );
            CourseDTO courseDTO = courseMapper.toCourseDTO(course);
            return new Response<>("Success", courseDTO);
        } catch (NotFoundException ex) {
            return new Response<>(
                    404,
                    ex.getMessage()
            );
        } catch (Exception ex) {
            return new Response<>(
                    500,
                    ex.getMessage()
            );
        }
    }

    public Response<CourseDTO> createCourse(CreateCourseDTO input) {
        try {
            if (courseRepository.existsByName(input.getName()))
                throw new AlreadyExistedException("Môn " + input.getName() + " đã tồn tại");
            Course course = courseMapper.toCreateCourseEntity(input);
            courseRepository.save(course);
            CourseDTO courseDTO = courseMapper.toCourseDTO(course);
            return new Response<>("Success", courseDTO);
        } catch (AlreadyExistedException ex) {
            return new Response<>(
                    400,
                    ex.getMessage()
            );
        } catch (Exception ex) {
            return new Response<>(
                    500,
                    ex.getMessage()
            );
        }
    }

    public Response<CourseDTO> updateCourse(String courseId, UpdateCourseDTO input) {
        try {
            Course course = courseRepository.findById(courseId).orElseThrow(
                    () -> new NotFoundException("Không tìm thấy môn học")
            );
            if (!course.getName().equals(input.getName()) && courseRepository.existsByName(input.getName()))
                throw new AlreadyExistedException("Môn " + input.getName() + " đã tồn tại");
            Course updatedCourse = courseMapper.toUpdateCourseEntity(course, input);
            courseRepository.save(updatedCourse);
            CourseDTO courseDTO = courseMapper.toCourseDTO(updatedCourse);
            return new Response<>("Success", courseDTO);
        } catch (NotFoundException ex) {
            return new Response<>(
                    404,
                    ex.getMessage()
            );
        } catch (AlreadyExistedException ex) {
            return new Response<>(
                    400,
                    ex.getMessage()
            );
        } catch (Exception ex) {
            return new Response<>(
                    500,
                    ex.getMessage()
            );
        }
    }

    public Response<Void> deleteCourse(String courseId) {
        try {
             Course course = courseRepository.findById(courseId).orElseThrow(
                     () -> new NotFoundException("Không tìm thấy môn học")
             );
             courseRepository.save(course);
             return new Response<>(200, "Xóa môn học thành công");
        } catch (NotFoundException ex) {
            return new Response<>(
                    404,
                    ex.getMessage()
            );
        } catch (Exception ex) {
            return new Response<>(
                    500,
                    ex.getMessage()
            );
        }
    }

    public Response<CourseDTO> deletedCourse(String courseId) {
        try {
            Course course = courseRepository.findById(courseId).orElseThrow(
                    () -> new NotFoundException("Không tìm thấy môn học")
            );
            course.setDeleted(true);
            courseRepository.save(course);
            CourseDTO courseDTO = courseMapper.toCourseDTO(course);
            return new Response<>("Success", courseDTO);
        } catch (NotFoundException ex) {
            return new Response<>(
                    404,
                    ex.getMessage()
            );
        } catch (Exception ex) {
            return new Response<>(
                    500,
                    ex.getMessage()
            );
        }
    }
}
