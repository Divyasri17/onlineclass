package com.example.demo.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Entity.Booking;

public interface BookingRepo
        extends JpaRepository<Booking, Integer> {

    List<Booking> findByParentId(int parentId);
}