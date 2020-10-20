package com.video.controller;

import com.video.pojo.User;
import com.video.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author: Administrator
 * @date: 2020/10/19 19:19
 */
@Controller
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("loginUser")
    @ResponseBody
    public String loginUser(User user, HttpServletRequest request) {
        String rst;

        if (userService.loginUser(user)) {
            // 登录成功
            rst = "success";
            HttpSession session = request.getSession(true);
            session.setAttribute("userAccount", user.getNickname());

            System.out.println(user);

            session.setMaxInactiveInterval(60 * 60);
        } else {
            // 登录失败
            rst = "fail";
        }

        return rst;
    }
}
