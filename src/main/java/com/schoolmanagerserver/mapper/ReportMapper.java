package com.schoolmanagerserver.mapper;

import com.schoolmanagerserver.pojos.CourseScoreDto;
import com.schoolmanagerserver.pojos.StudentInfoDto;
import com.schoolmanagerserver.pojos.TeacherCourseStudentDto;
import com.schoolmanagerserver.query.StudentQueryCondition;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ReportMapper {
    List<StudentInfoDto> queryStudentInfo(StudentQueryCondition condition);

    List<CourseScoreDto> findTeacherCourses(@Param("teacherName") String teacherName);

    List<TeacherCourseStudentDto> findTeacherCourseStudents(@Param("teacherName") String teacherName,
                                                            @Param("courseId") Long courseId);

    List<CourseScoreDto> findStudentScores(@Param("studentName") String studentName);
}

