package com.video.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.video.pojo.Course;
import com.video.pojo.QueryVo;
import com.video.pojo.Speaker;
import com.video.pojo.Video;
import com.video.service.CourseService;
import com.video.service.SpeakerService;
import com.video.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @author lyuf
 * @date 2020/10/19 19:51
 */
@RequestMapping("video")
@Controller
public class VideoController {

    @Autowired
    private VideoService videoService;

    @Autowired
    private SpeakerService speakerService;

    @Autowired
    private CourseService courseService;

    @RequestMapping("list")
    public ModelAndView list(@RequestParam(required = false) QueryVo queryVo, @RequestParam(value = "page", defaultValue = "1") Integer pageNum){
        PageHelper.startPage(pageNum, 10);
        List<Video> allVideo = videoService.findAllVideo(queryVo);
        PageInfo<Video> pageInfo = new PageInfo<>(allVideo);

        ModelAndView modelAndView = new ModelAndView();

        List<Speaker> speakerList = speakerService.findAll();
        modelAndView.addObject("speakerList",speakerList);
        List<Course> courseList = courseService.findAll();
        modelAndView.addObject("courseList",courseList);

        modelAndView.addObject("pageInfo",pageInfo);

        System.out.println(allVideo);
        modelAndView.setViewName("/behind/videoList.jsp");

        return modelAndView;
    }
}
