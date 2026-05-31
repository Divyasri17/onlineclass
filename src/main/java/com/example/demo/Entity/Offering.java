package com.example.demo.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "offering")
public class Offering {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "course_id")
    private int courseId;

    @Column(name = "teacher_id")
    private int teacherId;

    @Column(name = "batch_name")
    private String batchName;

    @Column(name = "status")
    private String status;
    
    @Column(name="price")
    private double price;

    @Column(name="mode")
    private String mode;

    @Column(name="level")
    private String level;
    
    @Column(name="duration_type")
    private String durationType;
    
    @Column(name="duration_count")
    private int durationCount;


    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

    public String getBatchName() {
        return batchName;
    }

    public void setBatchName(String batchName) {
        this.batchName = batchName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }
    

	public String getDurationType() {
		return durationType;
	}

	public void setDurationType(String durationType) {
		this.durationType = durationType;
	}

	public int getDurationCount() {
		return durationCount;
	}

	public void setDurationCount(int durationCount) {
		this.durationCount = durationCount;
	}
	
	
	

	public Offering(int courseId, int teacherId, String batchName, String status, double price, String mode,
			String level, String durationType, int durationCount) {
		super();
		this.courseId = courseId;
		this.teacherId = teacherId;
		this.batchName = batchName;
		this.status = status;
		this.price = price;
		this.mode = mode;
		this.level = level;
		this.durationType = durationType;
		this.durationCount = durationCount;
	}

	public Offering() {
    }
}