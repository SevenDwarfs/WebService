package team.sevendwarfs.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import team.sevendwarfs.common.Constant;
import team.sevendwarfs.common.ResponseState;
import team.sevendwarfs.common.SeatUtil;
import team.sevendwarfs.common.Util;
import team.sevendwarfs.persistence.entities.FilmOrder;
import team.sevendwarfs.persistence.entities.Screen;
import team.sevendwarfs.persistence.entities.User;
import team.sevendwarfs.persistence.service.FilmOrderService;
import team.sevendwarfs.persistence.service.ScreenService;
import team.sevendwarfs.persistence.service.UserService;
import team.sevendwarfs.web.model.OrderModel;
import team.sevendwarfs.web.model.Seat;
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

    @Autowired
    ScreenService screenService;

    @Autowired
    FilmOrderService filmOrderService;

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
        if (user == null) {
            return new UserModel();
        }

        return new UserModel(user);
    }

    @GetMapping("/order")
    @ResponseBody
    public OrderModel getOrder(HttpServletRequest request,
                               HttpServletResponse response) {
        User user = (User)request.getSession().getAttribute("user");
        if (user == null) {
            return new OrderModel();
        }

        user = userService.findOne(user.getId());

        return new OrderModel(user.getFilmOrderList());
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
    public ResponseState putUserInfo(@RequestParam(value = "username",
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
            String passMD5 = userService.findOne(user.getId()).getPasswordMD5();
            if (passMD5.equals(Util.MD5(oldPassword))) {
                user.setPasswordMD5(Util.MD5(newPassword));
            }
        }

        request.getSession().setAttribute("user", user);
        userService.update(user);

        return new ResponseState(ResponseState.SUCCESS);
    }


    /**
     * 锁定/出售座位
     * @param id    场次id
     * @param seat
     * @return
     */
    @PutMapping("/screen/{id}")
    @ResponseBody
    public ResponseState putSeat(@PathVariable("id") Integer id,
                                 @RequestParam(value = "seat") String seat,
                                 HttpServletRequest request,
                                 HttpServletResponse response) {

        User user = (User) request.getSession().getAttribute("user");
        if (user == null) { return new ResponseState(ResponseState.ERROR,
                "用户未登录"); }
        if (seat.length() != Constant.seatsNumber) {
            return new ResponseState(ResponseState.ERROR,
                    "提交字符串长度应为" + Constant.seatsNumber);
        }

        char type = SeatUtil.judgeType(seat);
        if (type != Constant.vacancy && type != Constant.lock &&
                type != Constant.sold) {
            return new ResponseState(ResponseState.ERROR, "提交字符串内容错误");
        }

        Screen screen = screenService.findById(id);
        if (screen == null) {
            return new ResponseState(ResponseState.ERROR, "该场次不存在");
        }
        if (screen.getSeats() == null) {
            screen.setSeats(Constant.vacancySeat);
        }

        StringBuffer seatBuffer = new StringBuffer(screen.getSeats());

        if (type == Constant.vacancy && !SeatUtil.validSeatVacancy(seat,
                seatBuffer)) {
            return new ResponseState(ResponseState.ERROR, "取消锁定失败，座位未锁定");
        }

        if (!SeatUtil.validSeatLock(seat, seatBuffer)) {
            return new ResponseState(ResponseState.ERROR, "锁定座位失败,座位已经被锁定或售出");
        }

        if (!SeatUtil.validSeatSold(seat, seatBuffer)) {
            return new ResponseState(ResponseState.ERROR, "购买座位失败,座位未被锁定");
        }

        /**
         * 更改场次的座位信息
         */
        SeatUtil.changeSeatState(seat, seatBuffer, type);
        screen.setSeats(new String(seatBuffer));
        screenService.update(screen);

        /**
         * 添加订单到用户状态中
         */
        if (type == Constant.sold) {
            FilmOrder filmOrder = new FilmOrder(user, screen.getId(), seat);
            user = userService.findOne(user.getId());

            filmOrderService.create(filmOrder);
            user.getFilmOrderList().add(filmOrder);
            userService.update(user);
        }

        return new ResponseState(ResponseState.SUCCESS);

//        User user = (User) request.getSession().getAttribute("user");
//        if (user == null) { return new ResponseState(ResponseState.ERROR,
//                "用户未登录"); }
//
//        Screen screen = screenService.findById(id);
//        StringBuffer seatBuffer = new StringBuffer(screen.getSeats());
//
//        if (SeatUtil.validSeatLock(seat, seatBuffer)) {
//            return new ResponseState(ResponseState.ERROR, "锁定座位失败,座位已经被锁定或售出");
//        }
//
//        if (SeatUtil.validSeatSold(seat, seatBuffer)) {
//            return new ResponseState(ResponseState.ERROR, "购买座位失败,座位未被锁定");
//        }
//
//        /**
//         * 更改场次的座位信息
//         */
//        SeatUtil.changeSeatState(seat, seatBuffer);
//        screen.setSeats(new String(seatBuffer));
//        screenService.update(screen);
//
//        /**
//         * 添加订单到用户状态中
//         */
//        FilmOrder filmOrder = new FilmOrder(user, screen.getId(), seat.toSeatForm());
//        user.getFilmOrderList().add(filmOrder);
//        userService.update(user);
//
//        return new ResponseState(ResponseState.SUCCESS);
    }
}
