package com.schoolmanagerserver.controller;

import com.schoolmanagerserver.pojos.CourseInfoDto;
import com.schoolmanagerserver.pojos.Result;
import com.schoolmanagerserver.pojos.StudentCourseDto;
import com.schoolmanagerserver.service.ScoreService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/score")
public class ScoreController {

    @Autowired
    private ScoreService scoreService;

    private final Logger logger = LoggerFactory.getLogger(ScoreController.class);

    /** 获取教师负责的课程列表 */
    @GetMapping("/teacher-courses")
    public Result<List<CourseInfoDto>> teacherCourses(@RequestParam String teacherName) {
        logger.info("/score/teacher-courses accessed teacherName={}", teacherName);
        return scoreService.listTeacherCourses(teacherName);
    }

    /** 获取某课程的所有学生及其成绩 */
    @GetMapping("/course-students")
    public Result<List<StudentCourseDto>> courseStudents(@RequestParam Long courseId) {
        logger.info("/score/course-students accessed courseId={}", courseId);
        return scoreService.listCourseStudents(courseId);
    }

    /** 批量更新学生成绩 */
    @PostMapping("/update-scores")
    public Result<List<StudentCourseDto>> updateScores(@RequestBody List<StudentCourseDto> list) {
        logger.info("/score/update-scores accessed size={}", list == null ? 0 : list.size());
        return scoreService.updateScores(list);
    }
}

