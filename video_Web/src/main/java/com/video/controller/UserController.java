package com.video.controller;

import com.video.pojo.User;
import com.video.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

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
        System.out.println("111111");
        String rst;
        User loginUser = userService.loginUser(user);

        if (loginUser != null) {
            // 登录成功
            rst = "success";
            HttpSession session = request.getSession(true);
            session.setAttribute("userAccount", loginUser.getNickname());
            session.setAttribute("user", loginUser);
            session.setMaxInactiveInterval(60 * 60);
        } else {
            // 登录失败
            rst = "fail";
        }

        return rst;
    }

    @RequestMapping("showMyProfile")
    public ModelAndView showMyProfile(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        HttpSession session = request.getSession(false);
        User sessionUser = (User) session.getAttribute("user");
        int id = sessionUser.getId();

        User user = userService.findUserById(id);

        modelAndView.addObject("user", user);
        modelAndView.setViewName("/before/my_profile.jsp");

        return modelAndView;
    }

    @RequestMapping("changeProfile")
    public String changeProfile() {
        return "/before/change_profile.jsp";
    }

    @RequestMapping("updateUser")
    public String updateUser(User user, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        User sessionUser = (User) session.getAttribute("user");
        userService.updateUser(user);

        sessionUser.setNickname(user.getNickname());
        sessionUser.setSex(user.getSex());
        sessionUser.setBirthday(user.getBirthday());
        sessionUser.setAddress(user.getAddress());

        session.setAttribute("user", sessionUser);

        return "/before/my_profile.jsp";
    }

    @RequestMapping("changeAvatar")
    public String changeAvatar() {
        return "/before/change_avatar.jsp";
    }

    @RequestMapping("upLoadImage")
    public String upLoadImage(MultipartFile image_file, HttpServletRequest request) throws IOException {
        HttpSession session = request.getSession(false);
        User sessionUser = (User) session.getAttribute("user");
        // 使用fileupload组件完成文件上传
        // 上传的位置
        String path = "E:\\soft\\tomcat\\apache-tomcat-9.0.33\\webapps\\video";
        // 判断，该路径是否存在
        File file = new File(path);

        // 说明上传文件项
        // 获取上传文件的名称
        String filename = image_file.getOriginalFilename();
        // 把文件的名称设置唯一值，uuid
        String uuid = UUID.randomUUID().toString().replace("-", "");
        filename = uuid+"_"+filename;
        // 完成文件上传
        image_file.transferTo(new File(path,filename));

        sessionUser.setImgurl(filename);
        userService.updateUser(sessionUser);

        session.setAttribute("user", sessionUser);

        return "/before/my_profile.jsp";
    }

    @RequestMapping("passwordSafe")
    public String passwordSafe(HttpServletRequest request) {
        return "/before/password_safe.jsp";

    }

    @RequestMapping("validatePassword")
    @ResponseBody
    public String validatePassword(String password, HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        String rst;

        if (user.getPassword().equals(password)) {
            rst = "success";
        } else {
            rst = "fail";
        }

        return rst;
    }

    @RequestMapping("updatePassword")
    public String updatePassword(HttpServletRequest request, String newPassword) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        user.setPassword(newPassword);
        userService.updateUser(user);

        session.setAttribute("user", user);

        return "/before/my_profile.jsp";

    }
}
