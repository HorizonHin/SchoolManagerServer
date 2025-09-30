package com.schoolmanagerserver.service.Impl;

import com.schoolmanagerserver.mapper.ScoreMapper;
import com.schoolmanagerserver.pojos.CourseInfoDto;
import com.schoolmanagerserver.pojos.Result;
import com.schoolmanagerserver.pojos.StudentCourseDto;
import com.schoolmanagerserver.service.ScoreService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ScoreServiceImpl implements ScoreService {

    @Autowired
    private ScoreMapper scoreMapper;

    private final Logger logger = LoggerFactory.getLogger(ScoreServiceImpl.class);

    @Override
    public Result<List<CourseInfoDto>> listTeacherCourses(String teacherName) {
        long start = System.currentTimeMillis();
        logger.info("[listTeacherCourses] START teacherName={}", teacherName);
        if (teacherName == null || teacherName.isBlank()) {
            logger.warn("[listTeacherCourses] INVALID_PARAM teacherName blank");
            return Result.fail("teacherName不能为空");
        }
        try {
            List<CourseInfoDto> list = scoreMapper.findCoursesByTeacher(teacherName);
            logger.info("[listTeacherCourses] SUCCESS size={}", list == null ? 0 : list.size());
            return Result.success(list);
        } catch (Exception e) {
            logger.error("[listTeacherCourses] EXCEPTION msg={}", e.getMessage(), e);
            return Result.fail("查询教师课程失败");
        } finally {
            logger.info("[listTeacherCourses] END cost={}ms", System.currentTimeMillis() - start);
        }
    }

    @Override
    public Result<List<StudentCourseDto>> listCourseStudents(Long courseId) {
        long start = System.currentTimeMillis();
        logger.info("[listCourseStudents] START courseId={}", courseId);
        if (courseId == null) {
            logger.warn("[listCourseStudents] INVALID_PARAM courseId null");
            return Result.fail("courseId不能为空");
        }
        try {
            List<StudentCourseDto> list = scoreMapper.findStudentsByCourseId(courseId);
            logger.info("[listCourseStudents] SUCCESS size={}", list == null ? 0 : list.size());
            return Result.success(list);
        } catch (Exception e) {
            logger.error("[listCourseStudents] EXCEPTION courseId={} msg={}", courseId, e.getMessage(), e);
            return Result.fail("查询课程学生失败");
        } finally {
            logger.info("[listCourseStudents] END courseId={} cost={}ms", courseId, System.currentTimeMillis() - start);
        }
    }

    @Override
    @Transactional
    public Result<List<StudentCourseDto>> updateScores(List<StudentCourseDto> studentCourses) {
        long start = System.currentTimeMillis();
        logger.info("[updateScores] START size={}", studentCourses == null ? 0 : studentCourses.size());
        if (studentCourses == null || studentCourses.isEmpty()) {
            logger.warn("[updateScores] INVALID_PARAM empty list");
            return Result.fail("更新列表不能为空");
        }
        try {
            int total = 0;
            for (StudentCourseDto dto : studentCourses) {
                if (dto.getId() == null) {
                    logger.warn("[updateScores] SKIP missing id dto={}", dto);
                    continue;
                }
                Integer score = dto.getScore();
                if (score != null && (score < 0 || score > 100)) {
                    logger.warn("[updateScores] SKIP invalid score id={} score={}", dto.getId(), score);
                    continue;
                }
                if (score != null) {
                    int affected = scoreMapper.updateScoreById(dto.getId(), score);
                    total += affected;
                }
            }
            logger.info("[updateScores] UPDATED rows={} (only rows with valid id & score counted)", total);
            // 收集相关课程ID重新查询，返回合并后的最新数据
            Set<Long> courseIds = studentCourses.stream()
                    .map(StudentCourseDto::getCourseId)
                    .filter(Objects::nonNull)
                    .collect(Collectors.toSet());
            List<StudentCourseDto> merged = new ArrayList<>();
            for (Long cid : courseIds) {
                merged.addAll(scoreMapper.findStudentsByCourseId(cid));
            }
            logger.info("[updateScores] SUCCESS returnSize={}", merged.size());
            return Result.success("更新完成", merged);
        } catch (Exception e) {
            logger.error("[updateScores] EXCEPTION msg={}", e.getMessage(), e);
            return Result.fail("批量更新成绩失败");
        } finally {
            logger.info("[updateScores] END cost={}ms", System.currentTimeMillis() - start);
        }
    }
}

