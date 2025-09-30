package com.schoolmanagerserver.controller;

import com.schoolmanagerserver.pojos.*;
import com.schoolmanagerserver.query.StudentQueryCondition;
import com.schoolmanagerserver.service.ReportService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/report")
public class ReportController {

    @Autowired
    private ReportService reportService;

    private final Logger logger = LoggerFactory.getLogger(ReportController.class);

    /** 查询学生信息（分页，管理员功能） */
    @PostMapping("/student-info")
    public Result<PageResult<StudentInfoDto>> queryStudentInfo(@RequestBody StudentQueryCondition condition) {
        logger.info("/report/student-info accessed condition={}", condition);
        return reportService.queryStudentInfo(condition);
    }

    /** 获取教师课程列表 */
    @GetMapping("/teacher-courses")
    public Result<List<CourseScoreDto>> teacherCourses(@RequestParam String teacherName) {
        logger.info("/report/teacher-courses accessed teacherName={}", teacherName);
        return reportService.getTeacherCourses(teacherName);
    }

    /** 查询教师课程的学生成绩 */
    @GetMapping("/teacher-course-students")
    public Result<List<TeacherCourseStudentDto>> teacherCourseStudents(@RequestParam String teacherName,
                                                                       @RequestParam Long courseId) {
        logger.info("/report/teacher-course-students accessed teacherName={} courseId={}", teacherName, courseId);
        return reportService.getTeacherCourseStudents(teacherName, courseId);
    }

    /** 查询学生个人成绩 */
    @GetMapping("/student-scores")
    public Result<List<CourseScoreDto>> studentScores(@RequestParam String studentName) {
        logger.info("/report/student-scores accessed studentName={}", studentName);
        return reportService.getStudentScores(studentName);
    }
}

