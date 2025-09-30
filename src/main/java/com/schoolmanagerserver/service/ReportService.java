package com.schoolmanagerserver.service;

import com.schoolmanagerserver.pojos.*;
import com.schoolmanagerserver.query.StudentQueryCondition;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ReportService {
    Result<PageResult<StudentInfoDto>> queryStudentInfo(StudentQueryCondition condition);
    Result<List<CourseScoreDto>> getTeacherCourses(String teacherName);
    Result<List<TeacherCourseStudentDto>> getTeacherCourseStudents(String teacherName, Long courseId);
    Result<List<CourseScoreDto>> getStudentScores(String studentName);
}

