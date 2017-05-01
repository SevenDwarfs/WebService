package team.sevendwarfs.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import team.sevendwarfs.common.Util;
import team.sevendwarfs.persistence.entities.User;
import team.sevendwarfs.persistence.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by deng on 2017/4/26.
 */
@Controller
public class LoginController {
    @Autowired
    UserService userService;

    /**
     * 登录操作
     * 1. 验证 username, password 域不为空
     * 2. 调用userService服务验证 用户名-密码是否对应
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
                return "登陆成功!\n" + user.toString();
            }


        }
        return "登陆失败!\n";
    }

    /**
     * 登陆的 GET 方法， 返回登陆页面
     * @return
     */
    @GetMapping(value = "/login")
    @ResponseBody
    public String loginGetMethod() {
        return "please login!\n";
    }

    /**
     * 注册的 GET 方法 返回注册页面
     * @return
     */
    @GetMapping(value = "/signup")
    @ResponseBody
    public String logupGetMethod() {
        return "sign up now!\n";
    }

    /**
     * 注册的 POST 方法
     */
    @PostMapping(value = "/signup")
    @ResponseBody
    public User logupPostMethod(@RequestParam(value = "username",
                                required = true) String username,
                                @RequestParam(value = "password",
                                required = true) String password,
                                HttpServletRequest request,
                                HttpServletResponse response) {
        /**
         * 1. 查询数据库，判断该用户名是否存在
         *      - 存在，注册失败，返回注册页面
         *      - 不存在
         *        新建用户实例，插入数据库
         *        同时添加Session，注册成功，转跳首页并登陆
         */
        User exitUser = userService.findByName(username);
        if (exitUser != null) return exitUser;

        User user = new User(username, Util.MD5(password));
        userService.create(user);
        request.getSession().setAttribute("user", user);
        return user;
    }
}
