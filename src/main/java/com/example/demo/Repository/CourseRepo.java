package com.example.demo.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Entity.Course;

@Repository
public interface CourseRepo
        extends JpaRepository<Course, Integer> {

    List<Course> findByTeacherId(int teacherId);

}