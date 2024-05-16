package com.example.cc106demosqlite1;

import java.io.Serializable;

public class Student implements Serializable {
    private int id;
    private String name;
    private String location;
    private String course;


    public Student() {
    }

    public Student(int id, String name, String location, String course) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.course = course;
    }

    public Student(String name, String location, String course) {
        this.name = name;
        this.location = location;
        this.course = course;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public String getCourse() {
        return course;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    @Override
    public String toString() {
        return "ID: " + this.id + "\n\tName: " + this.name + "\n\tLocation: " + this.location + "\n\tCourse: " + this.course;
    }
}
