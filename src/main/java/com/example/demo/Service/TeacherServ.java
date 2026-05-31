package com.example.demo.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.example.demo.Entity.Teacher;
import com.example.demo.Repository.TeacherRepo;

import jakarta.servlet.http.HttpSession;

@Service
public class TeacherServ {

    @Autowired
    private TeacherRepo repo;   
    
    // Get all teachers
    
    public List<Teacher> getAll() {
        return repo.findAll();
    }

    // Get teacher by id
    
    public Optional<Teacher> getById(int id) {
        return repo.findById(id);
    }

    // Insert teacher
    public Teacher insert(Teacher t) {

        Teacher existing =
                repo.findByEmail(t.getEmail());

        if(existing != null) {
            throw new RuntimeException(
                    "Email already exists");
        }

        return repo.save(t);
    }

    // Delete teacher
    
    public void deleteById(int id) {
        repo.deleteById(id);
    }

    // Update teacher
    
    public Teacher update(int id, Teacher t) {

        Optional<Teacher> existingTeacher = repo.findById(id);

        if (existingTeacher.isPresent()) {

            t.setId(id); // set existing id

            return repo.save(t);
        }

        throw new RuntimeException("Teacher not found with id: " + id);
    }
    
    
    
    // LOGIN 
    
    public String login(
            Teacher t,
            Model m,
            HttpSession session)
    {

        Teacher teacher =
            repo.findByEmailAndPassword(
                t.getEmail(),
                t.getPassword());

        if(teacher != null)
        {
            session.setAttribute(
                "teacher",
                teacher);

            // we are going to call methode which have get("/teacher") so even we can call teacher page directl y throught url
            return "redirect:/teacherhome";
        }

        m.addAttribute(
            "msg",
            "Invalid Email Or Password");

        return "login";
    }
    
    
    
    
    
    
}