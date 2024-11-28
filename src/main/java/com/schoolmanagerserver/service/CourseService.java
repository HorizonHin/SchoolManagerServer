package com.schoolmanagerserver.service;

import com.schoolmanagerserver.pojos.Course;
import com.schoolmanagerserver.pojos.Result;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CourseService {
      Result<List<Course>> getAll() ;
      Result<Void> delByIds(String ids);
}
