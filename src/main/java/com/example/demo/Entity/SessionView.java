package com.example.demo.Entity;

public class SessionView {

    private String teacherDate;
    private String teacherTime;

    private String parentDate;
    private String parentTime;
    
    private int id;

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getTeacherDate() {
        return teacherDate;
    }

    public void setTeacherDate(String teacherDate) {
        this.teacherDate = teacherDate;
    }

    public String getTeacherTime() {
        return teacherTime;
    }

    public void setTeacherTime(String teacherTime) {
        this.teacherTime = teacherTime;
    }

    public String getParentDate() {
        return parentDate;
    }

    public void setParentDate(String parentDate) {
        this.parentDate = parentDate;
    }

    public String getParentTime() {
        return parentTime;
    }

    public void setParentTime(String parentTime) {
        this.parentTime = parentTime;
    }
}