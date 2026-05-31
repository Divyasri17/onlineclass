package com.example.demo.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Entity.Teacher;
import com.example.demo.Service.TeacherServ;

@RestController
@RequestMapping("/teacher")
public class TeacherCont {

    @Autowired
    private TeacherServ serv;

    // Get all teachers
    
    @GetMapping("/all")
    public List<Teacher> getAllTeachers() {
        return serv.getAll();
    }

    // Get teacher by id
    
    @GetMapping("/{id}")
    public Optional<Teacher> getTeacherById(@PathVariable int id) {
        return serv.getById(id);
    }

    // Insert teacher
    
    @PostMapping("/add")
    public Teacher addTeacher(@RequestBody Teacher teacher) {
        return serv.insert(teacher);
    }

    // Update teacher
    
    @PutMapping("/update/{id}")
    public Teacher updateTeacher(@PathVariable int id,
                                 @RequestBody Teacher teacher) {
        return serv.update(id, teacher);
    }

    // Delete teacher
    
    @DeleteMapping("/delete/{id}")
    public String deleteTeacher(@PathVariable int id) {
        serv.deleteById(id);
        return "Teacher deleted successfully";

    }
	
    @GetMapping("/teacherhome")
    public String teacherHome() {
        return "teacherhome";
    }
    
   
}
