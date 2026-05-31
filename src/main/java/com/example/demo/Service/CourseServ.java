package com.example.demo.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.Course;
import com.example.demo.Entity.Offering;
import com.example.demo.Repository.CourseRepo;

@Service
public class CourseServ {

    @Autowired
    private CourseRepo repo;

    
    
    @Autowired
    private OfferingServ offeringServ;

    @Autowired
    private SessionServ sessionServ;
    
    
    public List<Course> getAll() {
        return repo.findAll();
    }

    public Optional<Course> getById(int id) {
        return repo.findById(id);
    }

    public Course insert(Course c) {
        return repo.save(c);
    }

    public Course update(int id, Course c) {

        Optional<Course> temp =
                repo.findById(id);

        if(temp.isPresent()) {

            c.setId(id);

            return repo.save(c);
        }

        throw new RuntimeException(
                "Course not found");
    }

    public void deleteById(int id) {

        repo.deleteById(id);
    }
    
    // get cource by teacher id
    public List<Course> getCoursesByTeacherId(
            int teacherId)
    {
        return repo.findByTeacherId(teacherId);
        		
    }
    
    
    public boolean canDeleteCourse(
            int courseId)
    {
        List<Offering> offerings =
                offeringServ.getAll()
                            .stream()
                            .filter(o ->
                                    o.getCourseId()
                                    == courseId)
                            .toList();

        for(Offering offering : offerings)
        {
            if(!sessionServ
                    .getSessionsByOfferingId(
                            offering.getId())
                    .isEmpty())
            {
                return false;
            }
        }

        if(!offerings.isEmpty())
        {
            return false;
        }

        return true;
    }}