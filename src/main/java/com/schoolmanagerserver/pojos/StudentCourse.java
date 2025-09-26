package com.schoolmanagerserver.pojos;

import lombok.Data;

import java.util.Date;

@Data
public class StudentCourse {
    // 选课记录主键（可选）
    private Long id;

    // 学生与课程关联
    private int studentId;
    private int courseId;

    // 学期（例如：2024-2025-1）
    private String semester;

    // 选课状态：0=已选，1=退选，2=已修完
    private Integer status;

    // 成绩（0-100）
    private Integer score;

    // 选课时间
    private Date selectTime;

    // 备注
    private String remark;

    public StudentCourse(int courseId , int studentId)  {
        this.courseId = courseId;
        this.studentId = studentId;
    }

    public StudentCourse() {
    }
}
