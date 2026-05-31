package com.example.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Entity.Offering;

@Repository
public interface OfferingRepo
        extends JpaRepository<Offering,Integer> {

}