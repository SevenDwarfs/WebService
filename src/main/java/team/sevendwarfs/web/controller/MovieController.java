package team.sevendwarfs.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import team.sevendwarfs.persistence.entities.Movie;
import team.sevendwarfs.persistence.service.MovieService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by deng on 2017/5/1.
 */
@Controller
@RequestMapping("/api/movie")
public class MovieController {
    @Autowired
    MovieService movieService;

    /**
     * 根据电影名查找电影，先查找中文名字，没有对应再查找英文名字
     * @param name
     * @return
     */
    @GetMapping("/name/{name}")
    @ResponseBody
    public Movie getMovieByName(@PathVariable("name") String name) {
        Movie movie = movieService.findByChineseName(name);
        if (movie == null) {
            movie = movieService.findByEnglishName(name);
        }
        return movie;
    }

    /**
     * 返回对应类型片电影列表，默认返回 id 往后的最多20条电影
     * @param type  URL中的类型片
     * @param id    查询键值对
     * @return
     */
    @GetMapping("/type/{type}")
    @ResponseBody
    public List<Movie> getMovieByType(@PathVariable("type") String type,
                                      @RequestParam(value = "id",
                                                    required = false,
                                              defaultValue = "0") Integer id) {
        return movieService.findByType(type, id);
    }

    /**
     * 根据上映日期查询电影
     * 日期格式 : 20170501
     * @param dateString
     * @return 电影列表
     */
    @GetMapping("/date/{date}")
    @ResponseBody
    public List<Movie> getMovieByDate(@PathVariable("date") String dateString) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Date date = null;
        try {
            date = sdf.parse(dateString);
        } catch (ParseException e) {
            return movieService.findByDate(new Date());
        }
        return movieService.findByDate(date);
    }

    /**
     * 返回最近一个月上映的电影
     * @return
     */
    @GetMapping("/showing")
    @ResponseBody
    public List<Movie> getMovieShowing() {
        return movieService.findShowing();
    }
}
