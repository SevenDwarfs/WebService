package team.sevendwarfs.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import team.sevendwarfs.common.Constant;
import team.sevendwarfs.persistence.entities.Cinema;
import team.sevendwarfs.persistence.entities.Movie;
import team.sevendwarfs.persistence.entities.Screen;
import team.sevendwarfs.persistence.service.CinemaService;
import team.sevendwarfs.persistence.service.MovieService;
import team.sevendwarfs.persistence.service.ScreenService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by deng on 2017/6/5.
 */

@Controller
@RequestMapping("/api/screen")
public class ScreenController {
    @Autowired
    ScreenService screenService;

    @Autowired
    MovieService movieService;

    @Autowired
    CinemaService cinemaService;

    /**
     * 通过影院和电影啦查询场次信息
     * @param cinemaId
     * @param movieId
     * @return
     */
    @GetMapping
    @ResponseBody
    public List<Screen> getScreenByCinemaAndMovie(@RequestParam(value="cinemaid") Integer cinemaId,
                                                  @RequestParam(value="movieid")
                                                          Integer movieId,
                                                  @RequestParam(value="date",
                                                          required = false)
                                                  String dateStr,
                                                  @RequestParam(value="time",
                                                          required = false)
                                                  String timeStr) {
        Cinema cinema = cinemaService.findById(cinemaId);
        Movie movie = movieService.findById(movieId);

        if (cinema == null || movie == null) {
            return new ArrayList<Screen>();
        }

        List<Screen> screenList = screenService.findByCinemaAndMovie(cinema, movie);
        if (screenList.size() == 0) {
            // 新建一个排片
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            SimpleDateFormat sdf2 = new SimpleDateFormat("hh:mm");
            Date date = null;
            Date time = null;
            try {
                date = sdf.parse(dateStr);
                time = sdf2.parse(timeStr);
            } catch (ParseException e) {
                date = new Date();
            }
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(time);
            calendar.add(Calendar.MINUTE, 30);
            Date next = calendar.getTime();
            String showTime = String.valueOf(time.getHours()) +
                    ":" + time.getMinutes() + "-" +
                    next.getHours() + ":" + next.getMinutes();

            Screen screen = new Screen(date, showTime, "国语", "1号厅", 38.00,
                    cinema, movie.getChineseName(), Constant.vacancySeat);
            screenService.create(screen);
            cinema.getScreens().add(screen);
            cinemaService.update(cinema);

            List<Screen> screens = new ArrayList<>();
            screens.add(screen);
            return screens;
        } else {
            for (Screen screen : screenList) {
                if (screen.getSeats() == null) {
                    screen.setSeats(Constant.vacancySeat);
                }
            }
            return screenList;
        }

    }

    @GetMapping("/{id}")
    @ResponseBody
    public Screen getScreenById(@PathVariable("id") Integer id) {
        Screen screen = screenService.findById(id);
        if (screen.getSeats() == null) { screen.setSeats(Constant.vacancySeat);}
        return screen;
    }



}
