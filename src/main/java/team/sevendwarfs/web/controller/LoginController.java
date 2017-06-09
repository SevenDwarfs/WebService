package team.sevendwarfs.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import team.sevendwarfs.common.ResponseState;
import team.sevendwarfs.common.Util;
import team.sevendwarfs.persistence.entities.User;
import team.sevendwarfs.persistence.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
    public ResponseState loginPostMethod(HttpServletRequest request,
                                  HttpServletResponse response) {
        String userName = request.getParameter("username");
        String password = request.getParameter("password");
        if (userName == null || password == null) {
            return new ResponseState(ResponseState.ERROR, "用户名或密码为空");
        }

        if (!"".equals(userName) && !"".equals(password)) {
            User user = userService.verify(userName, password);
            if (user != null) {
                request.getSession().setAttribute("user", user);
                return new ResponseState(ResponseState.SUCCESS);
            }


        }
        return new ResponseState(ResponseState.ERROR, "用户名或密码错误");

    }

    /**
     * 注册的 POST 方法
     */
    @PostMapping(value = "/signup")
    @ResponseBody
    public ResponseState logupPostMethod(@RequestParam(value = "username",
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
         * 表单格式校验
         */
        if (!Util.validPassword(password)) {
            return new ResponseState(ResponseState.ERROR, "密码格式不正确");
        } else if (!Util.validEmail(email)) {
            return new ResponseState(ResponseState.ERROR, "邮箱格式不正确");
        } else if (!Util.validPhone(phone)) {
            return new ResponseState(ResponseState.ERROR, "手机号码格式不正确");
        }

        /**
         * 查询是否已经注册过该用户
         */
        if (userService.findByName(username) != null) {
            return new ResponseState(ResponseState.ERROR, "用户名已被注册");
        } else if ( userService.findByEmail(email) != null) {
            return new ResponseState(ResponseState.ERROR, "邮箱已被注册");
        } else if ( userService.findByPhone(phone) != null) {
            return new ResponseState(ResponseState.ERROR, "手机号码已被注册");
        }

        /**
         * 用户注册
         */
        User user = new User(username, email, phone, Util.MD5(password));
        userService.create(user);
        request.getSession().setAttribute("user", user);

        return new ResponseState(ResponseState.SUCCESS);
    }

    @PutMapping(value = "/logout")
    @ResponseBody
    public ResponseState logoutPostMethod(HttpServletRequest request,
                                          HttpServletResponse response) {
        User user = (User)request.getSession().getAttribute("user");

        if (user == null) {
            return new ResponseState(ResponseState.ERROR, "不可重复登出");
        } else {
            request.removeAttribute("user");
            request.getSession().invalidate();
            return new ResponseState(ResponseState.SUCCESS);
        }
    }
}
