package com.video.controller;

import com.video.pojo.Speaker;
import com.video.pojo.Subject;
import com.video.service.SpeakerService;
import com.video.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;



@Controller
@RequestMapping("goodSir")
public class GoodSir {

    @Autowired
    private SubjectService subjectService;

    @Autowired
    private SpeakerService speakerService;


    /**
     * 关于网站介绍接口
     *
     * @param model
     * @return
     */
    @RequestMapping("aboutUs")
    public String aboutUs(Model model) {

        List<Subject> subjectList = subjectService.findAll();
        model.addAttribute("subjectList",subjectList);
        return "/before/aboutUs.jsp";
    }


    /**
     * 网站新闻页面接口
     * @param model
     * @return
     */
    @RequestMapping("news")
    public String news(Model model) {

        List<Subject> subjectList = subjectService.findAll();
        model.addAttribute("subjectList",subjectList);
        return "/before/news.jsp";
    }

    /**
     * 展示讲师页面接口
     * @param model
     * @return
     */
    @RequestMapping("showSpeakers")
    public String showTeachers(Model model) {

        List<Subject> subjectList = subjectService.findAll();
        model.addAttribute("subjectList",subjectList);

        List<Speaker> speakerList = speakerService.findAll();
        model.addAttribute("speakerList",speakerList);

        return "/before/showSpeakers.jsp";
    }



}
