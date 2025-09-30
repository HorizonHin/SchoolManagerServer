package com.schoolmanagerserver.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.schoolmanagerserver.mapper.ReportMapper;
import com.schoolmanagerserver.pojos.*;
import com.schoolmanagerserver.query.StudentQueryCondition;
import com.schoolmanagerserver.service.ReportService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    private ReportMapper reportMapper;

    private final Logger logger = LoggerFactory.getLogger(ReportServiceImpl.class);

    @Override
    public Result<PageResult<StudentInfoDto>> queryStudentInfo(StudentQueryCondition condition) {
        long start = System.currentTimeMillis();
        logger.info("[queryStudentInfo] START condition={}", condition);
        if (condition == null) {
            condition = new StudentQueryCondition();
        }
        int pageNum = condition.getPageNum() == null || condition.getPageNum() <= 0 ? 1 : condition.getPageNum();
        int pageSize = condition.getPageSize() == null || condition.getPageSize() <= 0 ? 10 : condition.getPageSize();
        try {
            PageHelper.startPage(pageNum, pageSize);
            List<StudentInfoDto> list = reportMapper.queryStudentInfo(condition);
            PageInfo<StudentInfoDto> pageInfo = new PageInfo<>(list);
            PageResult<StudentInfoDto> pr = new PageResult<>();
            pr.setRecords(list);
            pr.setTotal(pageInfo.getTotal());
            pr.setCurrent(pageNum);
            pr.setSize(pageSize);
            logger.info("[queryStudentInfo] SUCCESS total={} pageNum={} pageSize={}", pageInfo.getTotal(), pageNum, pageSize);
            return Result.success(pr);
        } catch (Exception e) {
            logger.error("[queryStudentInfo] EXCEPTION msg={}", e.getMessage(), e);
            PageResult<StudentInfoDto> empty = new PageResult<>();
            empty.setRecords(Collections.emptyList());
            empty.setTotal(0);
            empty.setCurrent(pageNum);
            empty.setSize(pageSize);
            return Result.fail("查询学生信息失败");
        } finally {
            logger.info("[queryStudentInfo] END cost={}ms", System.currentTimeMillis() - start);
        }
    }

    @Override
    public Result<List<CourseScoreDto>> getTeacherCourses(String teacherName) {
        long start = System.currentTimeMillis();
        logger.info("[getTeacherCourses] START teacherName={}", teacherName);
        if (teacherName == null || teacherName.isBlank()) {
            return Result.fail("teacherName不能为空");
        }
        try {
            List<CourseScoreDto> list = reportMapper.findTeacherCourses(teacherName);
            logger.info("[getTeacherCourses] SUCCESS size={}", list == null ? 0 : list.size());
            return Result.success(list);
        } catch (Exception e) {
            logger.error("[getTeacherCourses] EXCEPTION msg={}", e.getMessage(), e);
            return Result.fail("查询教师课程失败");
        } finally {
            logger.info("[getTeacherCourses] END cost={}ms", System.currentTimeMillis() - start);
        }
    }

    @Override
    public Result<List<TeacherCourseStudentDto>> getTeacherCourseStudents(String teacherName, Long courseId) {
        long start = System.currentTimeMillis();
        logger.info("[getTeacherCourseStudents] START teacherName={} courseId={}", teacherName, courseId);
        if (teacherName == null || teacherName.isBlank() || courseId == null) {
            return Result.fail("teacherName与courseId不能为空");
        }
        try {
            List<TeacherCourseStudentDto> list = reportMapper.findTeacherCourseStudents(teacherName, courseId);
            logger.info("[getTeacherCourseStudents] SUCCESS size={}", list == null ? 0 : list.size());
            return Result.success(list);
        } catch (Exception e) {
            logger.error("[getTeacherCourseStudents] EXCEPTION msg={}", e.getMessage(), e);
            return Result.fail("查询课程学生成绩失败");
        } finally {
            logger.info("[getTeacherCourseStudents] END cost={}ms", System.currentTimeMillis() - start);
        }
    }

    @Override
    public Result<List<CourseScoreDto>> getStudentScores(String studentName) {
        long start = System.currentTimeMillis();
        logger.info("[getStudentScores] START studentName={}", studentName);
        if (studentName == null || studentName.isBlank()) {
            return Result.fail("studentName不能为空");
        }
        try {
            List<CourseScoreDto> list = reportMapper.findStudentScores(studentName);
            logger.info("[getStudentScores] SUCCESS size={}", list == null ? 0 : list.size());
            return Result.success(list);
        } catch (Exception e) {
            logger.error("[getStudentScores] EXCEPTION msg={}", e.getMessage(), e);
            return Result.fail("查询学生成绩失败");
        } finally {
            logger.info("[getStudentScores] END cost={}ms", System.currentTimeMillis() - start);
        }
    }
}

