package com.example.demo.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.Entity.Course;
import com.example.demo.Entity.Teacher;
import com.example.demo.Service.CourseServ;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/course")
public class CourseCont {

    @Autowired
    private CourseServ serv;

    @GetMapping("/all")
    @ResponseBody
    public List<Course> getAll() {

        return serv.getAll();
    }

    @GetMapping("/{id}")
    @ResponseBody
    public Optional<Course> getById(
            @PathVariable int id) {

        return serv.getById(id);
    }

    @PostMapping("/add")
    @ResponseBody
    public Course addCourse(
            @RequestBody Course c) {

        return serv.insert(c);
    }

    @PutMapping("/update/{id}")
    @ResponseBody
    public Course updateCourse(
            @PathVariable int id,
            @RequestBody Course c) {

        return serv.update(id, c);
    }

    @GetMapping("/delete/{id}")
    public String deleteCourse(
            @PathVariable int id)
    {
        boolean canDelete =
                serv.canDeleteCourse(id);

        if(!canDelete)
        {
            return "redirect:/teacherhome?error=course";
        }

        serv.deleteById(id);

        return "redirect:/teacherhome";
    }    
    // taking to cource page and featching courec od that teacher
    
    @GetMapping("/courses")
    public String showCoursesPage(
            HttpSession session,
            Model m)
    {
        Teacher teacher =
                (Teacher)session.getAttribute("teacher");

        if(teacher == null)
        {
            return "redirect:/login";
        }

        List<Course> courses =
                serv.getCoursesByTeacherId(
                        teacher.getId());

        m.addAttribute(
                "courses",
                courses);

        return "Courcepage";
    }
    
    // getting cource by teacher id
    
    @GetMapping("/teacher/{teacherId}")
    @ResponseBody
    public List<Course> getCoursesByTeacherId(
            @PathVariable int teacherId)
    {
        return serv.getCoursesByTeacherId(
                teacherId);
    }
    
    
    
 // Open Add Course Page when teacher press add cource button  so it takes to add  cource page
    @GetMapping("/addpage")
    public String showAddCoursePage()
    {
        return "AddCourse";
    }
    
    // takes to addcource page to add cource
    
    @PostMapping("/save")
    public String saveCourse(
            Course course,
            HttpSession session)
    {
        Teacher teacher =
                (Teacher)session.getAttribute("teacher");

        course.setTeacherId(
                teacher.getId());

        Course savedCourse =
                serv.insert(course);

        return "redirect:/offering/offerings";
    }
    
    
    
    
    
}