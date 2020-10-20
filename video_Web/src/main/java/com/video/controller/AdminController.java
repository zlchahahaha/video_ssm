package com.video.controller;

import com.video.pojo.Admin;
import com.video.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @author lyuf
 * @date 2020/10/19 15:26
 */
@RequestMapping("admin")
@Controller
public class AdminController {

    @Autowired
    private AdminService adminService;


    @RequestMapping("toLoginView")
    public String toLoginView() {

        return "/behind/login.jsp";
    }

    @RequestMapping("login")
    @ResponseBody
    public String login(Admin admin) {

        System.out.println("执行登录");

        List<Admin> userByNameAndPsw = adminService.findUserByNameAndPsw(admin.getUsername(), admin.getUsername());

        return userByNameAndPsw.isEmpty() ? "fail" : "success";

    }
}