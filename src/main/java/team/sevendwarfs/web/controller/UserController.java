package team.sevendwarfs.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import team.sevendwarfs.common.Util;
import team.sevendwarfs.persistence.entities.User;
import team.sevendwarfs.persistence.service.UserService;
import team.sevendwarfs.web.model.UserModel;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 负责用户信息的控制器
 * Created by deng on 2017/6/3.
 */

@Controller
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    UserService userService;

    /**
     * 获取当前用户信息
     * @param request
     * @param response
     * @return
     */
    @GetMapping
    @ResponseBody
    public UserModel getUserInfo(HttpServletRequest request,
                            HttpServletResponse response) {
        User user = (User)request.getSession().getAttribute("user");
        return new UserModel(user);
    }

    /**
     * 修改当前用户信息, 没有添加头像功能
     * @param username
     * @param email
     * @param phone
     * @param oldPassword
     * @param newPassword
     * @param request
     * @param response
     * @return
     */
    @PutMapping
    @ResponseBody
    public UserModel putUserInfo(@RequestParam(value = "username",
                            required = false, defaultValue="") String username,
                            @RequestParam(value = "email",
                            required = false, defaultValue="") String email,
                            @RequestParam(value = "phone",
                            required = false, defaultValue="") String phone,
                            @RequestParam(value = "oldPassword",
                            required = false, defaultValue="") String oldPassword,
                            @RequestParam(value = "newPassword",
                            required = false, defaultValue="") String newPassword,
                            HttpServletRequest request,
                            HttpServletResponse response) {
        User user = (User) request.getSession().getAttribute("user");

        if (!"".equals(username) && userService.findByName(username) == null) {
            user.setUserName(username);
        }
        if (Util.validEmail(email) && userService.findByEmail(email) == null) {
            user.setEmail(email);
        }
        if (Util.validPhone(phone) && userService.findByPhone(phone) == null) {
            user.setPhone(phone);
        }
        if (Util.validPassword(newPassword)) {
            if (userService.findOne(user.getId()).getPasswordMD5()
                    .equals(Util.MD5(oldPassword))) {
                user.setPasswordMD5(Util.MD5(newPassword));
            }
        }

        request.getSession().setAttribute("user", user);
        userService.update(user);

        return new UserModel(user);
    }
}
