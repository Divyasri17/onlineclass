package com.example.demo.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.Booking;
import com.example.demo.Repository.BookingRepo;

@Service
public class BookingServ {

    @Autowired
    private BookingRepo repo;

    public List<Booking> getAll()
    {
        return repo.findAll();
    }

    public Optional<Booking> getById(int id)
    {
        return repo.findById(id);
    }

    public Booking save(Booking booking)
    {
        return repo.save(booking);
    }

    public void deleteById(int id)
    {
        repo.deleteById(id);
    }

    public boolean hasConflict(
            int parentId,
            LocalDateTime newStart,
            LocalDateTime newEnd)
    {
        List<Booking> bookings =
                repo.findByParentId(
                        parentId);

        for(Booking b : bookings)
        {
            if(newStart.isBefore(
                    b.getBookedEndTime())
                    &&
               newEnd.isAfter(
                    b.getBookedStartTime()))
            {
                return true;
            }
        }

        return false;
    }
}