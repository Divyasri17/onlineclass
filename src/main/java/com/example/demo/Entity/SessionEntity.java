package com.example.demo.Entity;

import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
@Table(name="session")
public class SessionEntity {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    @Column(name="offering_id")
    private int offeringId;

    @Column(name="start_time_utc")
    private LocalDateTime startTimeUtc;

    @Column(name="end_time_utc")
    private LocalDateTime endTimeUtc;

    public SessionEntity() {
    }

    public SessionEntity(int offeringId,
                   LocalDateTime startTimeUtc,
                   LocalDateTime endTimeUtc) {
        this.offeringId = offeringId;
        this.startTimeUtc = startTimeUtc;
        this.endTimeUtc = endTimeUtc;
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getOfferingId() {
		return offeringId;
	}

	public void setOfferingId(int offeringId) {
		this.offeringId = offeringId;
	}

	public LocalDateTime getStartTimeUtc() {
		return startTimeUtc;
	}

	public void setStartTimeUtc(LocalDateTime startTimeUtc) {
		this.startTimeUtc = startTimeUtc;
	}

	public LocalDateTime getEndTimeUtc() {
		return endTimeUtc;
	}

	public void setEndTimeUtc(LocalDateTime endTimeUtc) {
		this.endTimeUtc = endTimeUtc;
	}

    
}