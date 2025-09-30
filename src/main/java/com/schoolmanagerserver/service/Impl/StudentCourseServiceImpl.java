package com.schoolmanagerserver.service.Impl;

import com.schoolmanagerserver.mapper.StudentCourseMapper;
import com.schoolmanagerserver.pojos.CourseDto;
import com.schoolmanagerserver.pojos.Result;
import com.schoolmanagerserver.service.StudentCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Service
public class StudentCourseServiceImpl implements StudentCourseService {

    @Autowired
    private StudentCourseMapper studentCourseMapper;

    @Override
    public Result<List<CourseDto>> listAllSelectable() {
        List<CourseDto> list = studentCourseMapper.findAllSelectableCourses();
        return Result.success(list);
    }

    @Override
    public Result<List<CourseDto>> listSelected(String studentName) {
        if (studentName == null || studentName.isBlank()) {
            return Result.fail("studentName不能为空");
        }
        List<CourseDto> list = studentCourseMapper.findSelectedCourses(studentName);
        return Result.success(list);
    }

    @Override
    @Transactional
    public Result<List<CourseDto>> submitSelection(String studentName, List<Long> courseIds) {
        if (studentName == null || studentName.isBlank()) {
            return Result.fail("studentName不能为空");
        }
        studentCourseMapper.deleteSelectionsByStudent(studentName);
        if (courseIds != null && !courseIds.isEmpty()) {
            studentCourseMapper.batchInsertSelections(studentName, courseIds);
        }
        List<CourseDto> list = studentCourseMapper.findSelectedCourses(studentName);
        return Result.success(list);
    }

    @Override
    public Result<List<CourseDto>> clearSelection(String studentName) {
        if (studentName == null || studentName.isBlank()) {
            return Result.fail("studentName不能为空");
        }
        studentCourseMapper.deleteSelectionsByStudent(studentName);
        return Result.success(Collections.emptyList());
    }
}

