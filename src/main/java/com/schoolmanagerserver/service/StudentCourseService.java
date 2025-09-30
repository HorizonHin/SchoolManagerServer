package com.schoolmanagerserver.service;

import com.schoolmanagerserver.pojos.CourseDto;
import com.schoolmanagerserver.pojos.Result;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface StudentCourseService {
    Result<List<CourseDto>> listAllSelectable();
    Result<List<CourseDto>> listSelected(String studentName);
    Result<List<CourseDto>> submitSelection(String studentName, List<Long> courseIds);
    Result<List<CourseDto>> clearSelection(String studentName);
}
