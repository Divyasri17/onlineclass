package com.example.demo.Controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.Entity.Course;
import com.example.demo.Entity.Offering;
import com.example.demo.Entity.SessionEntity;
import com.example.demo.Entity.Teacher;
import com.example.demo.Service.CourseServ;
import com.example.demo.Service.OfferingServ;
import com.example.demo.Service.SessionServ;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/offering")
public class OfferingCont {

    @Autowired
    private OfferingServ serv;
    
    @Autowired
   public CourseServ courseServ;
    
    @Autowired
    private SessionServ sessionServ;

    @GetMapping("/all")
    @ResponseBody
    public List<Offering> getAll() {

        return serv.getAll();
    }

    @GetMapping("/{id}")
    @ResponseBody
    public Optional<Offering> getById(
            @PathVariable int id) {

        return serv.getById(id);
    }

    @PostMapping("/add")
    @ResponseBody
    public Offering addOffering(
            @RequestBody Offering o) {

        return serv.insert(o);
    }

    @PutMapping("/update/{id}")
    @ResponseBody
    public Offering updateOffering(
            @PathVariable int id,
            @RequestBody Offering o) {

        return serv.update(id, o);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseBody
    public String deleteOffering(
            @PathVariable int id) {

        serv.deleteById(id);

        return "Offering Deleted";
    }
    
    
    @GetMapping("/offerings")
    public String showOfferingPage(
            HttpSession session,
            Model m)
    {
        Teacher teacher =
            (Teacher)session.getAttribute("teacher");

        List<Course> courses =
            courseServ.getCoursesByTeacherId(
                teacher.getId());

        m.addAttribute("courses", courses);

        return "OfferingPage";
    }
    
        
    @GetMapping("/course/{courseId}")
    public String showCourseOfferings(
    		@PathVariable int courseId,
    		Model m)
    {
    	m.addAttribute("courseId", courseId);
    	
    	return "OfferingPage";
    }
    
    
    // here we are allowed to add offring to cource

    @GetMapping("/addpage")
    public String showAddOfferPage(
            @RequestParam int courseId,
            Model m)
    {
        m.addAttribute(
                "courseId",
                courseId);

        return "AddOffer";
    }
    
    
    @PostMapping("/save")
    public String saveOffering(
            Offering offering,
            @RequestParam LocalDateTime firstStartTime,
            @RequestParam LocalDateTime firstEndTime,
            HttpSession session)
    {
        Teacher teacher =
                (Teacher) session.getAttribute("teacher");

        offering.setTeacherId(
                teacher.getId());

        Offering savedOffering =
                serv.insert(offering);

        for(int i = 0;
            i < offering.getDurationCount();
            i++)
        {
            SessionEntity s =
                    new SessionEntity();

            s.setOfferingId(
                    savedOffering.getId());

            if("WEEK".equals(
                    offering.getDurationType()))
            {
                s.setStartTimeUtc(
                        firstStartTime.plusWeeks(i));

                s.setEndTimeUtc(
                        firstEndTime.plusWeeks(i));
            }
            else
            {
                s.setStartTimeUtc(
                        firstStartTime.plusDays(i));

                s.setEndTimeUtc(
                        firstEndTime.plusDays(i));
            }

            sessionServ.insert(s);
        }

        return "redirect:/session/sessions?offeringId="
        + savedOffering.getId();
    }}