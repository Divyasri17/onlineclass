package com.example.demo.Entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.*;

@Entity
@Table(name = "booking")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "parent_id")
    private int parentId;

    @Column(name = "course_id")
    private int courseId;

    @Column(name = "offering_id")
    private int offeringId;

    @Column(name = "session_id")
    private int sessionId;

    @Column(name = "booked_start_time")
    private LocalDateTime bookedStartTime;

    @Column(name = "booked_end_time")
    private LocalDateTime bookedEndTime;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    public Booking() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public int getOfferingId() {
        return offeringId;
    }

    public void setOfferingId(int offeringId) {
        this.offeringId = offeringId;
    }

    public int getSessionId() {
        return sessionId;
    }

    public void setSessionId(int sessionId) {
        this.sessionId = sessionId;
    }

    public LocalDateTime getBookedStartTime() {
        return bookedStartTime;
    }

    public void setBookedStartTime(LocalDateTime bookedStartTime) {
        this.bookedStartTime = bookedStartTime;
    }

    public LocalDateTime getBookedEndTime() {
        return bookedEndTime;
    }

    public void setBookedEndTime(LocalDateTime bookedEndTime) {
        this.bookedEndTime = bookedEndTime;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}