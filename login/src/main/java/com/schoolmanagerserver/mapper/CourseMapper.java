package com.schoolmanagerserver.mapper;


import com.schoolmanagerserver.pojos.Course;
import com.schoolmanagerserver.pojos.Result;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CourseMapper {

    int delByIdList(@Param("idList") List<Integer> idList);

    Result<List<Course>> getAll();
}
