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
import java.util.List;

/**
 * Created by deng on 2017/4/26.
 */
@Controller
@RequestMapping("/api")
public class LoginController {
    @Autowired
    UserService userService;

    /**
     * 登录操作
     * 1. 验证 username, password 域不为空
     * 2. 调用userService服务验证 用户名-密码是否对应
     *  - username 可以是 用户名／手机号／email
     * 3. 返回登陆结果
     * @param request
     * @param response
     * @return
     */
    @PostMapping(value = "/login")
    @ResponseBody
    public String loginPostMethod(HttpServletRequest request,
                                  HttpServletResponse response) {
        String userName = request.getParameter("username");
        String password = request.getParameter("password");
        if (userName == null || password == null) return "登录失败\n";

        if (!"".equals(userName) && !"".equals(password)) {
            User user = userService.verify(userName, password);
            if (user != null) {
                request.getSession().setAttribute("user", user);
                // TODO 这里不能返回用户信息，仅供开发时使用
                return "登陆成功!\n" + new UserModel(user).toString();
            }


        }
        return "登陆失败!\n";
    }

    /**
     * 注册的 POST 方法
     */
    @PostMapping(value = "/signup")
    @ResponseBody
    public String logupPostMethod(@RequestParam(value = "username",
                                required = true) String username,
                                @RequestParam(value = "email",
                                required = true) String email,
                                @RequestParam(value = "phone",
                                required = true) String phone,
                                @RequestParam(value = "password",
                                required = true) String password,
                                HttpServletRequest request,
                                HttpServletResponse response) {
        /**
         * 1. 查询数据库，判断该用户名是否存在
         *      - 存在，注册失败，返回注册页面
         *      - 不存在
         *        新建用户实例，插入数据库
         *        同时添加Session，注册成功
         */
        User exitUser = userService.findByName(username);
        if (userService.findByName(username) != null) {
            return "该用户名已被注册";
        } else if ( userService.findByEmail(email) != null) {
            return "邮箱已被注册";
        } else if ( userService.findByPhone(phone) != null) {
            return "该手机已被注册";
        }


        User user = new User(username, email, phone, Util.MD5(password));
        userService.create(user);
        request.getSession().setAttribute("user", user);

        // TODO 这里开发时候写
        return "注册成功" + new UserModel(user).toString();
    }

    @PostMapping(value = "/logout")
    @ResponseBody
    public String logoutPostMethod(HttpServletRequest request,
                                  HttpServletResponse response) {
        User user = (User)request.getSession().getAttribute("user");

        if (user == null) {
            return "不可以重复登出";
        } else {
            request.getSession().invalidate();
            return "登出成功";
        }
    }
}
