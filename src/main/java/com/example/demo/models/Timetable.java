package com.example.demo.models;

import jakarta.persistence.*;

@Entity
public class Timetable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int studentid;
    private String dayofweek;
    private String coursename;

    public Timetable() {
    }

    public Timetable(int studentid, String dayofweek, String coursename) {
        this.studentid = studentid;
        this.dayofweek = dayofweek;
        this.coursename = coursename;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStudentid() {
        return studentid;
    }

    public void setStudentid(int studentid) {
        this.studentid = studentid;
    }

    public String getDayofweek() {
        return dayofweek;
    }

    public void setDayofweek(String dayofweek) {
        this.dayofweek = dayofweek;
    }

    public String getCoursename() {
        return coursename;
    }

    public void setCoursename(String coursename) {
        this.coursename = coursename;
    }
}