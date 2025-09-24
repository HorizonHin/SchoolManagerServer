package com.schoolmanagerserver.controller;

import com.schoolmanagerserver.pojos.Course;
import com.schoolmanagerserver.pojos.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import com.schoolmanagerserver.service.CourseService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/course")
public class CourseController {

    @Autowired
    private CourseService courseService;


    @GetMapping("/getAll")
    public Result<List<Course>> getAll() {
        return courseService.getAll();
    }

    @DeleteMapping("/del/{ids}")
    public Result<Void> delByIds(@PathVariable("ids") String ids) {
        return courseService.delByIds(ids);
    }
}
