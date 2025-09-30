package com.schoolmanagerserver.pojos;

import lombok.Data;

/**
 * 基础课程信息（用于教师课程、成绩管理模块返回）
 */
@Data
public class CourseInfoDto {
    private Long courseId;
    private String courseName;
    private String teacher; // 对应 course.teacher_name
}

