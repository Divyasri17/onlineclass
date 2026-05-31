package com.example.demo.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.SessionEntity;
import com.example.demo.Repository.SessionRepo;



@Service
public class SessionServ {

    @Autowired
    private SessionRepo repo;

    public List<SessionEntity> getAll()
    {
        return repo.findAll();
    }

    public Optional<SessionEntity> getById(int id)
    {
        return repo.findById(id);
    }

    public SessionEntity insert(SessionEntity  s)
    {
        return repo.save(s);
    }

    public SessionEntity update(int id, SessionEntity s)
    {
        Optional<SessionEntity> temp = repo.findById(id);

        if(temp.isPresent())
        {
            s.setId(id);
            return repo.save(s);
        }

        throw new RuntimeException("Session Not Found");
    }

    public void deleteById(int id)
    {
        repo.deleteById(id);
    }
    
    public List<SessionEntity>
    getSessionsByOfferingId(
            int offeringId)
    {
        return repo.findByOfferingId(
                offeringId);
    }
}