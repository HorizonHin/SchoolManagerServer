package com.schoolmanagerserver.service.Impl;

import com.schoolmanagerserver.mapper.StudentCourseMapper;
import com.schoolmanagerserver.pojos.CourseDto;
import com.schoolmanagerserver.pojos.Result;
import com.schoolmanagerserver.service.StudentCourseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Service
public class StudentCourseServiceImpl implements StudentCourseService {

    @Autowired
    private StudentCourseMapper studentCourseMapper;

    private Logger logger = LoggerFactory.getLogger(StudentCourseServiceImpl.class);

    @Override
    public Result<List<CourseDto>> listAllSelectable() {
        long start = System.currentTimeMillis();
        logger.info("[listAllSelectable] START");
        try {
            List<CourseDto> list = studentCourseMapper.findAllSelectableCourses();
            logger.info("[listAllSelectable] SUCCESS size={}", list == null ? 0 : list.size());
            return Result.success(list);
        } catch (Exception e) {
            logger.error("[listAllSelectable] EXCEPTION msg={}", e.getMessage(), e);
            return Result.fail("查询可选课程失败");
        } finally {
            logger.info("[listAllSelectable] END cost={}ms", System.currentTimeMillis() - start);
        }
    }

    @Override
    public Result<List<CourseDto>> listSelected(String studentName) {
        long start = System.currentTimeMillis();
        logger.info("[listSelected] START studentName={} ", studentName);
        if (studentName == null || studentName.isBlank()) {
            logger.warn("[listSelected] INVALID_PARAM studentName is blank");
            return Result.fail("studentName不能为空");
        }
        try {
            List<CourseDto> list = studentCourseMapper.findSelectedCourses(studentName);
            logger.info("[listSelected] SUCCESS studentName={} size={}", studentName, list == null ? 0 : list.size());
            return Result.success(list);
        } catch (Exception e) {
            logger.error("[listSelected] EXCEPTION studentName={} msg={}", studentName, e.getMessage(), e);
            return Result.fail("查询已选课程失败");
        } finally {
            logger.info("[listSelected] END studentName={} cost={}ms", studentName, System.currentTimeMillis() - start);
        }
    }

    @Override
    @Transactional
    public Result<List<CourseDto>> submitSelection(String studentName, List<Long> courseIds) {
        long start = System.currentTimeMillis();
        logger.info("[submitSelection] START studentName={} courseIds={} size={}", studentName, courseIds, courseIds == null ? 0 : courseIds.size());
        if (studentName == null || studentName.isBlank()) {
            logger.warn("[submitSelection] INVALID_PARAM studentName is blank");
            return Result.fail("studentName不能为空");
        }
        try {
            int deleted = studentCourseMapper.deleteSelectionsByStudent(studentName);
            logger.info("[submitSelection] DELETED oldSelections count={} studentName={}", deleted, studentName);
            int inserted = 0;
            if (courseIds != null && !courseIds.isEmpty()) {
                inserted = studentCourseMapper.batchInsertSelections(studentName, courseIds);
            }
            logger.info("[submitSelection] INSERTED newSelections count={} studentName={}", inserted, studentName);
            List<CourseDto> list = studentCourseMapper.findSelectedCourses(studentName);
            logger.info("[submitSelection] SUCCESS studentName={} finalSize={}", studentName, list == null ? 0 : list.size());
            return Result.success(list);
        } catch (Exception e) {
            logger.error("[submitSelection] EXCEPTION studentName={} msg={}", studentName, e.getMessage(), e);
            return Result.fail("提交选课失败");
        } finally {
            logger.info("[submitSelection] END studentName={} cost={}ms", studentName, System.currentTimeMillis() - start);
        }
    }

    @Override
    public Result<List<CourseDto>> clearSelection(String studentName) {
        long start = System.currentTimeMillis();
        logger.info("[clearSelection] START studentName={}", studentName);
        if (studentName == null || studentName.isBlank()) {
            logger.warn("[clearSelection] INVALID_PARAM studentName is blank");
            return Result.fail("studentName不能为空");
        }
        try {
            int deleted = studentCourseMapper.deleteSelectionsByStudent(studentName);
            logger.info("[clearSelection] DELETED count={} studentName={}", deleted, studentName);
            logger.info("[clearSelection] SUCCESS studentName={} returning empty list", studentName);
            return Result.success(Collections.emptyList());
        } catch (Exception e) {
            logger.error("[clearSelection] EXCEPTION studentName={} msg={}", studentName, e.getMessage(), e);
            return Result.fail("清空选课失败");
        } finally {
            logger.info("[clearSelection] END studentName={} cost={}ms", studentName, System.currentTimeMillis() - start);
        }
    }
}

