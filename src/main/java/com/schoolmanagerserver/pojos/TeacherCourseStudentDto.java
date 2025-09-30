package com.schoolmanagerserver.pojos;

import lombok.Data;

import java.util.Date;

@Data
public class TeacherCourseStudentDto {
    private String username;
    private String realName;   // 学生姓名
    private String courseName; // 课程名
    private String semester;   // 学期
    private Integer score;     // 成绩
    private Integer status;    // 状态 0=已选 1=退选 2=已修完
    private Date selectTime;   // 选课时间
    private String remark;     // 备注
}

