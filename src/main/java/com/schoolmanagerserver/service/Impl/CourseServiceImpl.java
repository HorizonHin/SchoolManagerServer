package com.schoolmanagerserver.service.Impl;

import com.schoolmanagerserver.mapper.CourseMapper;
import com.schoolmanagerserver.pojos.Course;
import com.schoolmanagerserver.pojos.Result;
import com.schoolmanagerserver.service.CourseService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    private CourseMapper courseMapper;


    @Override
    public Result<List<Course>> getAll() {
        return courseMapper.getAll();
    }

    @Override
    public Result<Void> delByIds(String ids) {
        //
        List<Integer> idList = Arrays.stream(ids.split(",")).filter(s -> !s.isEmpty())
                .map(Integer::parseInt)
                .toList();
        if (idList.isEmpty()) {
            return Result.fail("没有数据");
        }
        int deletedCount = courseMapper.delByIdList(idList);
        if (deletedCount > 0) {
            return Result.success();
        }
        else
        {
            return Result.fail("删除失败");
        }
    }

}

























