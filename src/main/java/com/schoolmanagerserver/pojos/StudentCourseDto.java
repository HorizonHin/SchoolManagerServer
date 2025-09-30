package com.schoolmanagerserver.pojos;

import lombok.Data;

import java.util.Date;

/**
 * DTO for score management (extends student_course info with courseName).
 */
@Data
public class StudentCourseDto {
    private Long id;            // 选课记录主键
    private Long courseId;      // 课程ID
    private String studentName; // 学生
    private String courseName;  // 课程名称
    private String semester;    // 学期
    private Integer status;     // 状态：0=已选，1=退选，2=已修完
    private Integer score;      // 成绩 0-100
    private Date selectTime;    // 选课时间
    private String remark;      // 备注
}
