package com.schoolmanagerserver.pojos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentCourse {
    // 选课记录主键（可选）
    private Long id;
    // 课程ID（新增，便于与课程关联）
    private Long courseId;
    // 学生与课程关联
    private String studentName;
    private String courseName;

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
}

