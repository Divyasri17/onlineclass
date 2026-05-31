package com.example.demo.Controller;

import java.time.ZoneId;
import java.time.ZonedDateTime;
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

import com.example.demo.Entity.Offering;
import com.example.demo.Entity.SessionEntity;
import com.example.demo.Entity.Teacher;
import com.example.demo.Service.OfferingServ;
import com.example.demo.Service.SessionServ;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/session")
public class SessionCont {

    @Autowired
    private SessionServ serv;
    
    @Autowired
    private OfferingServ offeringServ;
    

    // Get All Sessions

    @GetMapping("/all")
    @ResponseBody
    public List<SessionEntity> getAll()
    {
        return serv.getAll();
    }

    // Get Session By Id

    @GetMapping("/{id}")
    @ResponseBody
    public Optional<SessionEntity> getById(
            @PathVariable int id)
    {
        return serv.getById(id);
    }

    // Insert Session

    @PostMapping("/add")
    @ResponseBody
    public SessionEntity addSession(
            @RequestBody SessionEntity s)
    {
        return serv.insert(s);
    }

    // Update Session

    @PutMapping("/update/{id}")
    @ResponseBody
    public SessionEntity updateSession(
            @PathVariable int id,
            @RequestBody SessionEntity s)
    {
        return serv.update(id, s);
    }

    // Delete Session

    @DeleteMapping("/delete/{id}")
    @ResponseBody
    public String deleteSession(
            @PathVariable int id)
    {
        serv.deleteById(id);

        return "Session Deleted Successfully";
    }
    
    @GetMapping("/sessions")
    public String showSessionsPage(
            @RequestParam int offeringId,
            Model m)
    {
        List<SessionEntity> sessions =
                serv.getSessionsByOfferingId(
                        offeringId);

        m.addAttribute(
                "sessions",
                sessions);

        m.addAttribute(
                "offeringId",
                offeringId);

        return "SessionPage";
    }
    
    @GetMapping("/addpage")
    public String showAddSessionPage(
            @RequestParam int offeringId,
            Model m)
    {
        m.addAttribute("offeringId", offeringId);

        return "AddSession";
    }
    
//    @PostMapping("/save")
//    public String saveSession(
//            SessionEntity session)
//    {
//    	if(session.getEndTimeUtc()
//    	        .isBefore(session.getStartTimeUtc())
//    	    ||
//    	   session.getEndTimeUtc()
//    	        .isEqual(session.getStartTimeUtc()))
//    	{
//    	    return "redirect:/session/addpage?offeringId="
//    	            + session.getOfferingId();
//    	}
//
//        serv.insert(session);
//
//        return "redirect:/session/sessions?offeringId="
//                + session.getOfferingId();
//    }
    
    
    @GetMapping("/offeringlist")
    public String showOfferingList(
            HttpSession session,
            Model m)
    {
        Teacher teacher =
                (Teacher) session.getAttribute("teacher");

        if (teacher == null) {
            return "redirect:/login";
        }

        List<Offering> offerings =
                offeringServ.getAll()
                        .stream()
                        .filter(o -> o.getTeacherId() == teacher.getId())
                        .toList();

        m.addAttribute("offerings", offerings);

        return "SessionPage";
    }
    
    
    @PostMapping("/save")
    public String saveSession(
            SessionEntity session,
            HttpSession httpSession)
    {
    	if(session.getEndTimeUtc().isBefore(session.getStartTimeUtc())
    			   ||
    			   session.getEndTimeUtc().isEqual(session.getStartTimeUtc()))
    			{
    			    return "redirect:/session/addpage?offeringId="
    			            + session.getOfferingId()
    			            + "&error=invalid";
    			}
    	
    	Teacher teacher =
    	        (Teacher) httpSession
    	        .getAttribute("teacher");

    	ZoneId teacherZone =
    	        ZoneId.of(
    	                teacher.getTimezone());

    	ZonedDateTime startTeacher =
    	        session.getStartTimeUtc()
    	               .atZone(teacherZone);

    	ZonedDateTime endTeacher =
    	        session.getEndTimeUtc()
    	               .atZone(teacherZone);

    	session.setStartTimeUtc(
    	        startTeacher
    	        .withZoneSameInstant(
    	                ZoneId.of("UTC"))
    	        .toLocalDateTime());

    	session.setEndTimeUtc(
    	        endTeacher
    	        .withZoneSameInstant(
    	                ZoneId.of("UTC"))
    	        .toLocalDateTime());

        serv.insert(session);

        return "redirect:/session/success";    
        
    }
    
    

@GetMapping("/success")
public String sessionSaved()
{
    return "SessionSaved";
}

















}
