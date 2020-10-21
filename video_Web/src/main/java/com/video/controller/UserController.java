package com.video.controller;

import com.video.pojo.User;
import com.video.service.UserService;
import com.video.utils.MailUtils;
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

import static com.video.utils.MailUtils.getValidateCode;

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

    @RequestMapping("loginOut")
    @ResponseBody
    public String loginOut(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.invalidate();
        session.setMaxInactiveInterval(0);

        return "false";

    }

    @RequestMapping("loginOut2")
    public String loginOut2(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.invalidate();
        session.setMaxInactiveInterval(0);

        return "redirect:/index.jsp";

    }

    @RequestMapping("showMyProfile")
    public ModelAndView showMyProfile(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        HttpSession session = request.getSession();
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
        filename = uuid + "_" + filename;
        // 完成文件上传
        image_file.transferTo(new File(path, filename));

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

    @RequestMapping("forgetPassword")
    public String forgetPassword() {
        return "/before/forget_password.jsp";
    }

    @RequestMapping("sendEmail")
    @ResponseBody
    public String sendEmail(String email, HttpServletRequest request) {
        User user = userService.findUserByEmail(email);
        String rst;

        if (user == null) {
            rst = "hasNoUser";
        } else {
            rst = "success";
            String checkCode = getValidateCode(6);
            request.getSession(false).setAttribute("checkCode", checkCode);
            System.out.println("验证码：" + checkCode);
            MailUtils.sendMail(email, "你好，这是一封测试邮件，无需回复。", "测试邮件随机生成的验证码是：" + checkCode);
        }

        return rst;

    }

    @RequestMapping("validateEmailCode")
    public String validateEmailCode(String email, String code, HttpServletRequest request) {
        HttpSession session = request.getSession();
        String checkCode = (String) session.getAttribute("checkCode");
        String rst;

        if (checkCode.equals(code)) {
            session.setAttribute("email", email);
            rst = "/before/reset_password.jsp";
        } else {
            rst = "/before/forget_password.jsp";
        }

        return rst;

    }

    @RequestMapping("resetPassword")
    public String resetPassword(String password, HttpServletRequest request) {
        HttpSession session = request.getSession();
        String email = (String) session.getAttribute("email");

        User user = userService.findUserByEmail(email);
        user.setPassword(password);

        userService.updateUser(user);

        return "redirect:/subject/selectAll";
    }

    @RequestMapping("insertUser")
    @ResponseBody
    public String insertUser(String yzm, User user, HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        userService.insertUser(user);
        session.setAttribute("user", userService.findUserByEmail(user.getEmail()));

        return "success";
    }

    @RequestMapping("validateEmail")
    @ResponseBody
    public String validateEmail(String email) {
        User checkedUser = userService.findUserByEmail(email);
        String rst = "";

        if (checkedUser == null) {
            rst = "success";
        }

        return rst;
    }
}
