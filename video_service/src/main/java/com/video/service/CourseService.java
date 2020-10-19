package com.video.service;

import com.video.pojo.Course;
import com.video.pojo.Subject;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * @author: Administrator
 * @date: 2020/10/19 19:54
 */
public interface CourseService {
    public List<Course> course(@PathVariable(name="subject.id") Integer subjectId);


}
