package team.sevendwarfs.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import team.sevendwarfs.common.Util;
import team.sevendwarfs.persistence.entities.Movie;
import team.sevendwarfs.persistence.service.MovieService;
import team.sevendwarfs.web.model.SimpMovie;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

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
    public SimpMovie getMovieByName(@PathVariable("name") String name) {
        Movie movie = movieService.findByChineseName(name);
        if (movie == null) {
            movie = movieService.findByEnglishName(name);
        }

        if (movie == null) {
            return new SimpMovie();
        }
        return new SimpMovie(movie);
    }

    /**
     * 返回对应类型片电影列表，默认返回 id 往后的最多20条电影
     * @param type  URL中的类型片
     * @param id    查询键值对
     * @return
     */
    @GetMapping("/type/{type}")
    @ResponseBody
    public List<SimpMovie> getMovieByType(@PathVariable("type") String type,
                                      @RequestParam(value = "id",
                                                    required = false,
                                              defaultValue = "0") Integer id) {
        List<Movie> movies = movieService.findByType(type, id);
        return Util.MoviesToSimpMovies(movies);
    }

    /**
     * 根据上映日期查询电影
     * 日期格式 : 20170501
     * @param dateString
     * @return 电影列表
     */
    @GetMapping("/date/day/{date}")
    @ResponseBody
    public List<SimpMovie> getMovieByDayDate(@PathVariable("date") String
                                                   dateString) {
        List<Movie> movies = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Date date = null;
        try {
            date = sdf.parse(dateString);
        } catch (ParseException e) {
            return new ArrayList<>();
        }
        movies = movieService.findByDayDate(date);

        return Util.MoviesToSimpMovies(movies);
    }

    /**
     * 查询给定月份上映的电影
     * 日期格式 : 201705
     * @param dateString
     * @return 电影列表
     */
    @GetMapping("/date/month/{date}")
    @ResponseBody
    public List<SimpMovie> getMovieByMonthDate(@PathVariable("date") String
                                                   dateString) {
        List<Movie> movies = null;

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
        Date date = null;
        try {
            date = sdf.parse(dateString);
        } catch (ParseException e) {
            return new ArrayList<>();
        }
        movies = movieService.findByMonthDate(date);

        return Util.MoviesToSimpMovies(movies);
    }

    /**
     * 查询给定年份的上映电影
     * 日期格式 : 2017
     * @param dateString
     * @return 电影列表
     */
    @GetMapping("/date/year/{date}")
    @ResponseBody
    public List<SimpMovie> getMovieByYearDate(@PathVariable("date") String
                                                   dateString) {
        List<Movie> movies = null;

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        Date date = null;
        try {
            date = sdf.parse(dateString);
        } catch (ParseException e) {
            return new ArrayList<>();
        }
        movies = movieService.findByYearDate(date);

        return Util.MoviesToSimpMovies(movies);
    }


    /**
     * 返回最近一个月上映的电影
     * @param number 控制返回的数量
     * @return
     */
    @GetMapping("/showing/{number}")
    @ResponseBody
    public List<SimpMovie> getMovieShowing(@PathVariable("number") Integer number) {
        List<Movie> movies = movieService.findShowing();

        if (movies.size() <= number) { return Util.MoviesToSimpMovies(movies); }

        movies.sort(new Comparator<Movie>() {
            @Override
            public int compare(Movie o1, Movie o2) {
                if (o2.getReleaseDate().after(o1.getReleaseDate())) {
                    return 1;
                } else if (o2.getReleaseDate().equals(o1.getReleaseDate())) {
                    return 0;
                } else {
                    return -1;
                }
            }
        });

        movies = movies.subList(0, number);
        return Util.MoviesToSimpMovies(movies);
    }

    /**
     * 根据电影ID查询电影
     * @param strId
     * @return
     */
    @GetMapping("/{id}")
    @ResponseBody
    public Movie getMovieInfo(@PathVariable("id") String strId) {
        int id = 5;
        try {
            id = Integer.parseInt(strId);
        } catch (NumberFormatException e) {
            return null;
        }
        Movie movie = movieService.findById(id);
        return movie;
    }


    @GetMapping("/query")
    @ResponseBody
    public List<SimpMovie> getQueryMovie(@RequestParam(value="type")
                                                     String type,
                                         @RequestParam(value="area")
                                         String area,
                                         @RequestParam(value="year")
                                         String yearStr,
                                         @RequestParam(value="page")
                                         String pageStr,
                                         @RequestParam(value="step")
                                         String stepStr) {
        if (yearStr.equals("all")) { yearStr = "0"; }

        int page = 0;
        int step = 0;
        int year = 0;
        try {
            page = Integer.parseInt(pageStr);
            step = Integer.parseInt(stepStr);
            year = Integer.parseInt(yearStr);
        } catch (NumberFormatException e) {
            return new ArrayList<SimpMovie>();
        }

        List<Movie> movieList = movieService.findByTypeAndCountry(type, area);

        movieService.filterMovieByYear(movieList, year);


        int len = movieList.size();
        int beginIndex = page * step;
        int endIndex = (beginIndex + step) > len ? len : (beginIndex + step);

        return Util.MoviesToSimpMovies(movieList.subList(beginIndex, endIndex));
    }

    @GetMapping("/query/count")
    @ResponseBody
    public Integer getMovieCount(@RequestParam(value="type") String type,
                                 @RequestParam(value="area") String area,
                                 @RequestParam(value="year") String yearStr) {
        if (yearStr.equals("all")) { yearStr = "0"; }
        int year = 0;
        try {
            year = Integer.parseInt(yearStr);
        } catch (NumberFormatException e) {
            return 0;
        }

        List<Movie> movieList = movieService.findByTypeAndCountry(type, area);

        movieService.filterMovieByYear(movieList, year);


        return movieList.size();
    }

}
