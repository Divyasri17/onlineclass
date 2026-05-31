package com.example.demo.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.Entity.Course;
import com.example.demo.Entity.Offering;
import com.example.demo.Entity.Teacher;
import com.example.demo.Service.CourseServ;
import com.example.demo.Service.OfferingServ;
import com.example.demo.Service.SessionServ;
import com.example.demo.Service.TeacherServ;

import jakarta.servlet.http.HttpSession;

	
	@Controller
	public class LoginControler {
		
		
		@Autowired
		private CourseServ courseServ;

		@Autowired
		private OfferingServ offeringServ;

		@Autowired
		private SessionServ sessionServ;

	    @Autowired
	    TeacherServ serv;

	    @GetMapping("/login")
	    public String showLoginPage() {
	        return "Login";
	    }

	    @PostMapping("/login")
	    public String login(Teacher t,
	                        Model m,
	                        HttpSession session) {

	        return serv.login(t, m, session);
	    }

	    
	    
	    // this is used when user correctly enter the detaies and want to go to teacher home page
	     // here we are getting session which we stored during login and use here to this session to get name of teacher 
	    @GetMapping("/teacherhome")
	    public String teacherHome(HttpSession session,
	                              Model m)
	    {
	        Teacher teacher =
	            (Teacher) session.getAttribute("teacher");

	        if(teacher == null)
	        {
	            return "redirect:/login";
	        }

	        int totalCourses =
	                courseServ
	                .getCoursesByTeacherId(
	                        teacher.getId())
	                .size();

	        List<Offering> offerings =
	                offeringServ.getAll()
	                            .stream()
	                            .filter(o ->
	                                    o.getTeacherId()
	                                    == teacher.getId())
	                            .toList();

	        int totalOfferings =
	                offerings.size();

	        int totalSessions = 0;

	        for(Offering offering : offerings)
	        {
	            totalSessions +=
	                    sessionServ
	                    .getSessionsByOfferingId(
	                            offering.getId())
	                    .size();
	        }

	        m.addAttribute(
	                "teacher",
	                teacher);

	        m.addAttribute(
	                "totalCourses",
	                totalCourses);

	        m.addAttribute(
	                "totalOfferings",
	                totalOfferings);

	        m.addAttribute(
	                "totalSessions",
	                totalSessions);
	        
	        List<Course> courses =
	                courseServ.getCoursesByTeacherId(
	                        teacher.getId());

	        for(Course course : courses)
	        {
	            int offeringCount = 0;
	            int sessionCount = 0;

	            List<Offering> courseOfferings =
	                    offerings.stream()
	                             .filter(o ->
	                                     o.getCourseId()
	                                     == course.getId())
	                             .toList();

	            offeringCount = courseOfferings.size();

	            for(Offering offering : courseOfferings)
	            {
	                sessionCount +=
	                        sessionServ
	                        .getSessionsByOfferingId(
	                                offering.getId())
	                        .size();
	            }

	            course.setOfferingCount(
	                    offeringCount);

	            course.setSessionCount(
	                    sessionCount);
	        }

	        m.addAttribute(
	                "courses",
	                courses);
	        return "TeachersHome";
	    }
	    
	    // used when u want to log out and go to login page
	    
	    @GetMapping("/logout")
	    public String logout(HttpSession session)
	    {
	        // Teacher clicked logout.
	        // Destroy the current session so the application
	        // forgets which teacher is logged in.
	        session.invalidate();

	        // After logout, send the user to the login page.
	        return "redirect:/login";
	    }
	    
	}
