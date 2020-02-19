package com.github.perscholas.model;

import javax.persistence.Entity;
import javax.persistence.Id;

// TODO - Annotate and Implement respective interface and define behaviors
@Entity
public class Course {
    @Id
    Integer id;
    String name;
    String instructor;

    public Course() {
    }

    public Course(Integer id, String name, String instructor) {
        this.id = id;
        this.name = name;
        this.instructor = instructor;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInstructor() {
        return instructor;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }
}
