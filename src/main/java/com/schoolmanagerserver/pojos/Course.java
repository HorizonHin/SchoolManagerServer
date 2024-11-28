package com.schoolmanagerserver.pojos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Course {
    private Long id;
    private String name;
    private Integer courseNum;
    private String courseTime;
    private int courseHours;
    private int credit;

    private Long teacherId;
    private String teacherName;

    private int capacity;
    private int classSize;

    // 构造方法（可选）

    public Course(){

    }

}
