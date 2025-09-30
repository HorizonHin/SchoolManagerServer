package com.schoolmanagerserver.service;

import com.schoolmanagerserver.pojos.CourseInfoDto;
import com.schoolmanagerserver.pojos.Result;
import com.schoolmanagerserver.pojos.StudentCourseDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ScoreService {
    Result<List<CourseInfoDto>> listTeacherCourses(String teacherName);
    Result<List<StudentCourseDto>> listCourseStudents(Long courseId);
    Result<List<StudentCourseDto>> updateScores(List<StudentCourseDto> studentCourses);
}