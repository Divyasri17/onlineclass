package com.example.demo.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.Entity.Course;
import com.example.demo.Entity.Offering;
import com.example.demo.Entity.Parent;
import com.example.demo.Service.CourseServ;
import com.example.demo.Service.OfferingServ;
import com.example.demo.Service.ParentServ;
import com.example.demo.Service.SessionServ;
import com.example.demo.Entity.SessionEntity;

import com.example.demo.Entity.Teacher;
import com.example.demo.Service.TeacherServ;


import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import com.example.demo.Entity.SessionView;

import jakarta.servlet.http.HttpSession;

@Controller
public class ParentController {

    @Autowired
    private ParentServ serv;
    
    @Autowired
    private CourseServ courseServ;

    @Autowired
    private OfferingServ offeringServ;

    @Autowired
    private SessionServ sessionServ;
    
    @Autowired
    private TeacherServ teacherServ;


    @GetMapping("/parent/login")
    public String showParentLoginPage()
    {
        return "ParentLogin";
    }

    @PostMapping("/parent/login")
    public String login(
            Parent parent,
            Model m,
            HttpSession session)
    {
        return serv.login(
                parent,
                m,
                session);
    }

    @GetMapping("/parent/signup")
    public String showSignupPage()
    {
        return "ParentSignup";
    }

    @PostMapping("/parent/signup")
    public String signup(
            Parent parent,
            Model m)
    {
        return serv.signup(
                parent,
                m);
    }

    @GetMapping("/parenthome")
    public String parentHome(
            HttpSession session,
            Model m)
    {
        Parent parent =
                (Parent) session.getAttribute("parent");

        if(parent == null)
        {
            return "redirect:/parent/login";
        }

        m.addAttribute(
                "parent",
                parent);

        m.addAttribute(
                "courses",
                courseServ.getAll());

        return "ParentHome";
    }

    @GetMapping("/parent/logout")
    public String logout(
            HttpSession session)
    {
        session.invalidate();

        return "redirect:/parent/login";
    }
    
    
    @GetMapping("/parent/course/{courseId}")
    public String courseDetails(
            @PathVariable int courseId,
            HttpSession session,
            Model m)
    {
        Parent parent =
                (Parent) session.getAttribute("parent");

        if(parent == null)
        {
            return "redirect:/parent/login";
        }

        Course course =
                courseServ.getById(courseId).get();

        Offering offering =
                offeringServ.getAll()
                            .stream()
                            .filter(o ->
                                    o.getCourseId()
                                    == courseId)
                            .findFirst()
                            .orElse(null);
        
        Teacher teacher =
                teacherServ
                .getById(
                        offering.getTeacherId())
                .get();
        
        m.addAttribute(
                "teacherTimezone",
                teacher.getTimezone());
        
        List<SessionEntity> sessions =
                sessionServ
                .getSessionsByOfferingId(
                        offering.getId());

        List<SessionView> sessionViews =
                new ArrayList<>();
        
        
        
        ZoneId teacherZone =
                ZoneId.of(
                        teacher.getTimezone());

        ZoneId parentZone =
                ZoneId.of(
                        parent.getTimezone());

        DateTimeFormatter dateFormat =
                DateTimeFormatter.ofPattern(
                        "dd MMM yyyy");

        DateTimeFormatter timeFormat =
                DateTimeFormatter.ofPattern(
                        "hh:mm a");
        
        
        for(SessionEntity s : sessions)
        {
            SessionView view =
                    new SessionView();

            ZonedDateTime teacherStart =
                    s.getStartTimeUtc()
                     .atZone(ZoneId.of("UTC"))
                     .withZoneSameInstant(
                             teacherZone);

            ZonedDateTime teacherEnd =
                    s.getEndTimeUtc()
                     .atZone(ZoneId.of("UTC"))
                     .withZoneSameInstant(
                             teacherZone);

            ZonedDateTime parentStart =
                    s.getStartTimeUtc()
                     .atZone(ZoneId.of("UTC"))
                     .withZoneSameInstant(
                             parentZone);

            ZonedDateTime parentEnd =
                    s.getEndTimeUtc()
                     .atZone(ZoneId.of("UTC"))
                     .withZoneSameInstant(
                             parentZone);
            view.setTeacherDate(
                    teacherStart.format(
                            dateFormat));

            view.setTeacherTime(
                    teacherStart.format(
                            timeFormat)
                    + " - "
                    +
                    teacherEnd.format(
                            timeFormat));

            view.setParentDate(
                    parentStart.format(
                            dateFormat));

            view.setParentTime(
                    parentStart.format(
                            timeFormat)
                    + " - "
                    +
                    parentEnd.format(
                            timeFormat));
            
            view.setId(
                    s.getId());

            sessionViews.add(view);
        }

        m.addAttribute(
                "course",
                course);

        m.addAttribute(
                "offering",
                offering);

        m.addAttribute(
                "sessions",
                sessionViews);

        m.addAttribute(
                "parentTimezone",
                parent.getTimezone());

        return "CourseDetails";
    }
}