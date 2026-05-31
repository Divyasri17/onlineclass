package com.example.demo.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.Offering;
import com.example.demo.Repository.OfferingRepo;
import com.example.demo.Repository.SessionRepo;

@Service
public class OfferingServ {

    @Autowired
    private OfferingRepo repo;

    public List<Offering> getAll() {
        return repo.findAll();
    }

    public Optional<Offering> getById(int id) {
        return repo.findById(id);
    }

    public Offering insert(Offering o) {
        return repo.save(o);
    }

    public Offering update(int id, Offering o) {

        Optional<Offering> temp =
                repo.findById(id);

        if(temp.isPresent()) {

            o.setId(id);

            return repo.save(o);
        }

        throw new RuntimeException(
                "Offering Not Found");
    }

    public void deleteById(int id) {

        repo.deleteById(id);
    }
    

    @Autowired
    private SessionRepo sessionRepo;

    // Create this method here

    public String getOfferingStatus(int offeringId)
    {
        LocalDateTime latestEnd =
                sessionRepo.findLatestEndTime(offeringId);

        if(latestEnd == null)
        {
            return "NO SESSIONS";
        }

        if(latestEnd.isBefore(LocalDateTime.now()))
        {
            return "INACTIVE";
        }

        return "ACTIVE";
    }

}