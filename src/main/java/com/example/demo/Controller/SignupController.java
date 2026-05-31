package com.example.demo.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.Entity.Teacher;
import com.example.demo.Service.TeacherServ;

@Controller
public class SignupController {

    @Autowired
    private TeacherServ serv;

    @GetMapping("/signup")
    public String signupPage() {
        return "Signup";
    }

    @PostMapping("/signup")
    public String signup(Teacher teacher,
                         Model model) {

        serv.insert(teacher);

        model.addAttribute(
                "msg",
                "Registration Successful");

        return "Login";
    }
}