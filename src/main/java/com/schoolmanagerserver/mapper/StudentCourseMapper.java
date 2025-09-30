package com.schoolmanagerserver.mapper;

import com.schoolmanagerserver.pojos.CourseDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface StudentCourseMapper {

    List<CourseDto> findAllSelectableCourses();

    List<CourseDto> findSelectedCourses(@Param("studentName") String studentName);

    int deleteSelectionsByStudent(@Param("studentName") String studentName);

    int batchInsertSelections(@Param("studentName") String studentName, @Param("courseIds") List<Long> courseIds);
}

