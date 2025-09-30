package com.schoolmanagerserver.pojos;

import lombok.Data;

/**
 * Simplified course dto for student selection frontend.
 */
@Data
public class CourseDto {
    private Long courseId;    // 新增: 课程ID
    private String username;   // student username (nullable for all courses list)
    private String coursename; // course name
    private String teacher;    // teacher name
}
