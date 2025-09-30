package com.schoolmanagerserver.mapper;

import com.schoolmanagerserver.pojos.CourseInfoDto;
import com.schoolmanagerserver.pojos.StudentCourseDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ScoreMapper {
    List<CourseInfoDto> findCoursesByTeacher(@Param("teacherName") String teacherName);
    List<StudentCourseDto> findStudentsByCourseId(@Param("courseId") Long courseId);
    int updateScoreById(@Param("id") Long id, @Param("score") Integer score);
}

