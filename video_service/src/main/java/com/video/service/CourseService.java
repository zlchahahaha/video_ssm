package com.video.service;

import com.video.pojo.Course;

import java.util.List;

/**
 * @author lyuf
 * @date 2020/10/19 21:48
 */
public interface CourseService {
    List<Course> findAll();
}
