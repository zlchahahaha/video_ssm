package com.video.controller;

import com.video.pojo.Admin;
import com.video.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author lyuf
 * @date 2020/10/19 15:26
 */
@RequestMapping("admin")
@Controller
public class AdminController {
    @Autowired
    private AdminService adminService;

    @RequestMapping("login")
    public ModelAndView login(Admin admin,@RequestParam(defaultValue = "fail",required = false) String data) {
        ModelAndView modelAndView = new ModelAndView();
        Admin userByNameAndPsw = adminService.findUserByNameAndPsw(admin.getUsername(), admin.getUsername());

        if (userByNameAndPsw != null) {
            data = "success";
            modelAndView.addObject("data", data);
            modelAndView.setViewName("/WEB-INF/jsp/login.jsp");
            return modelAndView;
        } else {
            modelAndView.addObject("data", data);
            modelAndView.setViewName("/WEB-INF/jsp/login.jsp");
            return modelAndView;
        }
    }

}
