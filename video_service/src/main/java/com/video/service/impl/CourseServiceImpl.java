package com.video.service.impl;

import com.video.dao.CourseMapper;
import com.video.pojo.Course;
import com.video.pojo.CourseExample;
import com.video.pojo.Subject;
import com.video.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * @author: Administrator
 * @date: 2020/10/19 19:54
 */
@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    private CourseMapper courseMapper;

    @Override
    public List<Course> course(@PathVariable(name="subject.id") Integer subjectId) {
        CourseExample courseExample = new CourseExample();
        CourseExample.Criteria criteria = courseExample.createCriteria();
        criteria.andSubjectIdEqualTo(subjectId);
        List<Course> courseList = courseMapper.selectByExample(courseExample);

        return courseList;
    }

}
