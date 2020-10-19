package com.video.controller;

import com.video.pojo.Subject;
import com.video.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @author: Administrator
 * @date: 2020/10/19 16:00
 */
@Controller
@RequestMapping("subject")
public class SubjectController {
    @Autowired
    private SubjectService subjectService;

    @RequestMapping("selectAll")
    public ModelAndView selectAll() {
        ModelAndView modelAndView = new ModelAndView();
        List<Subject> subjectList = subjectService.findAll();

        modelAndView.addObject("subjectList", subjectList);
        modelAndView.setViewName("/before/index.jsp");

        return modelAndView;
    }
}
