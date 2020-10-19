package com.video.service.impl;

import com.video.dao.CourseMapper;
import com.video.pojo.Course;
import com.video.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lyuf
 * @date 2020/10/19 21:48
 */
@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    private CourseMapper courseMapper;

    @Override
    public List<Course> findAll() {
        return courseMapper.selectByExample(null);
    }
}
