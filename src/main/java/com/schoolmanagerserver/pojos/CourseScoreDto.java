package com.schoolmanagerserver.pojos;

import lombok.Data;

import java.util.Date;

@Data
public class CourseScoreDto {
    private Long courseId;
    private String courseName;
    private String teacher;
    private String semester; // 可能为空（教师课程列表时）
    private Integer score;   // 课程成绩（学生或教师视角）
    private Integer status;  // 0=已选，1=退选，2=已修完
    private Date selectTime;
    private String remark;
}

