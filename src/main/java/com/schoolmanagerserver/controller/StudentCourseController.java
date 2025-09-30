package com.schoolmanagerserver.controller;

import com.schoolmanagerserver.pojos.CourseDto;
import com.schoolmanagerserver.pojos.Result;
import com.schoolmanagerserver.service.StudentCourseService;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentCourseController {

    @Autowired
    private StudentCourseService studentCourseService;

    private Logger logger = LoggerFactory.getLogger(StudentCourseController.class);

    /**
     * 获取所有可选课程
     */
    @GetMapping("/courses")
    public Result<List<CourseDto>> listAllCourses() {
        logger.info("/student/courses 接口被访问");
        return studentCourseService.listAllSelectable();
    }

    /**
     * 获取某学生已选择的课程
     */
    @GetMapping("/selectedCourses")
    public Result<List<CourseDto>> listSelected(@RequestParam String studentName
   ) {
        logger.info("/student/selectedCourses 接口被访问，studentName={}", studentName);
        return studentCourseService.listSelected(studentName);
    }

    /**
     * 提交选课（覆盖式提交）
     */
    @PostMapping("/selectedCourses")
    public Result<List<CourseDto>> submit(@RequestBody CourseSelectionRequest req) {
        logger.info("/student/selectedCourses 接口被访问，studentName={}, courseIds={}",
                req.getStudentName(), req.getCourseIds());
        return studentCourseService.submitSelection(req.getStudentName(), req.getCourseIds());
    }

    /**
     * 清空选课
     */
    @DeleteMapping("/selectedCourses")
    public Result<List<CourseDto>> clear(@RequestParam String studentName) {
        logger.info("/student/selectedCourses 接口被访问，studentName={}", studentName);
        return studentCourseService.clearSelection(studentName);
    }

    @Data
    public static class CourseSelectionRequest {
        private String studentName;
        private List<Long> courseIds;
    }
}

