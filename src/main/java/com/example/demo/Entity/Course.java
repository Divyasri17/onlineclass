package com.example.demo.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "course")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "image_url")
    private String imageUrl;
    
    
    @Column(name="teacher_id")
    private int teacherId;

    
    
    
    @Transient
    private int offeringCount;

    @Transient
    private int sessionCount;
    
    
    public Course() {
    }

    public Course(String title,
                  String description,
                  String imageUrl,int teacherId) {

        this.title = title;
        this.description = description;
        this.imageUrl = imageUrl;
        this.teacherId=teacherId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

	public int getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(int teacherId) {
		this.teacherId = teacherId;
	}
    
	
	public int getOfferingCount() {
	    return offeringCount;
	}

	public void setOfferingCount(int offeringCount) {
	    this.offeringCount = offeringCount;
	}

	public int getSessionCount() {
	    return sessionCount;
	}

	public void setSessionCount(int sessionCount) {
	    this.sessionCount = sessionCount;
	}
    
}