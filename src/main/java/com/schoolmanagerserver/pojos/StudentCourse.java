package com.schoolmanagerserver.pojos;

import lombok.Data;

@Data
public class StudentCourse {
    private int studentId;
    private int courseId;

    public StudentCourse(int courseId , int studentId)  {
        this.courseId = courseId;
        this.studentId = studentId;
    }

    public StudentCourse() {
    }
}
