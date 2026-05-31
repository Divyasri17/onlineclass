package com.example.demo.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.Entity.Booking;
import com.example.demo.Entity.Parent;
import com.example.demo.Entity.SessionEntity;
import com.example.demo.Service.BookingServ;
import com.example.demo.Service.SessionServ;

import jakarta.servlet.http.HttpSession;

@Controller
public class BookingController {

    @Autowired
    private BookingServ bookingServ;

    @Autowired
    private SessionServ sessionServ;

    @PostMapping("/booking/book")
    public String bookCourse(
            int courseId,
            int offeringId,
            HttpSession session)
    {
        Parent parent =
                (Parent) session.getAttribute(
                        "parent");

        if(parent == null)
        {
            return "redirect:/parent/login";
        }

        List<SessionEntity> sessions =
                sessionServ
                .getSessionsByOfferingId(
                        offeringId);

        for(SessionEntity s : sessions)
        {
            boolean conflict =
                    bookingServ.hasConflict(
                            parent.getId(),
                            s.getStartTimeUtc(),
                            s.getEndTimeUtc());

            if(conflict)
            {
                return "redirect:/parent/course/"
                        + courseId
                        + "?error=conflict";
            }
        }

        for(SessionEntity s : sessions)
        {
            Booking booking =
                    new Booking();

            booking.setParentId(
                    parent.getId());

            booking.setCourseId(
                    courseId);

            booking.setOfferingId(
                    offeringId);

            booking.setSessionId(
                    s.getId());

            booking.setBookedStartTime(
                    s.getStartTimeUtc());

            booking.setBookedEndTime(
                    s.getEndTimeUtc());

            bookingServ.save(
                    booking);
        }

        return "redirect:/parenthome?success=true";
    }}