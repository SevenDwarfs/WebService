package team.sevendwarfs.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import team.sevendwarfs.common.Util;
import team.sevendwarfs.persistence.entities.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by deng on 2017/4/26.
 */
@Controller
public class LoginController {
    /**
     * 登录操作
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public User loginPostMethod(HttpServletRequest request, HttpServletResponse
            response) {
        String userName = request.getParameter("username");
        String password = request.getParameter("password");
        if (!"".equals(userName) && !"".equals(password)) {
            User user = new User(userName, Util.MD5(password));
            request.getSession().setAttribute("user", user);
        }
        return new User(userName, password);
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    @ResponseBody
    public String loginGetMethod() {
        return "please login!";
    }
}
