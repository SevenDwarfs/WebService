package team.sevendwarfs.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import team.sevendwarfs.common.Util;
import team.sevendwarfs.persistence.entities.Cinema;
import team.sevendwarfs.persistence.entities.Movie;
import team.sevendwarfs.persistence.entities.Screen;
import team.sevendwarfs.persistence.service.CinemaService;
import team.sevendwarfs.persistence.service.MovieService;
import team.sevendwarfs.web.model.SimpCinema;
import team.sevendwarfs.web.model.SimpMovie;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by deng on 2017/6/5.
 */

@Controller
@RequestMapping("/api/cinema")
public class CinemaController {
    @Autowired
    CinemaService cinemaService;

    @Autowired
    MovieService movieService;

    /**
     * 根据地址获取影院简要信息
     * @param number    查询语句中 选填 默认为10
     * @param address   查询语句中 必填
     * @return
     */
    @GetMapping
    @ResponseBody
    public List<SimpCinema> getSimpCinemaByAddress(
            @RequestParam(value="number", required=false, defaultValue="10") Integer number,
            @RequestParam(value="address") String address) {
        List<Cinema> cinemas = cinemaService.findByAddress(address);

        if (cinemas.size() > number) {
            cinemas = cinemas.subList(0, number);
        }

        return Util.CinemaToSimpCinema(cinemas);
    }

    /**
     * 根据id返回详细的影院信息
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    @ResponseBody
    public Cinema getCinemaById(@PathVariable("id") Integer id) {
        return cinemaService.findById(id);
    }

    /**
     * 返回该影院有排片的电影
     * @param id 电影ID
     * @return
     */
    @GetMapping("/showing")
    @ResponseBody
    public List<SimpMovie> getSimpMovieShowing(@RequestParam(value="id")
                                               Integer id) {
        List<Screen> screens = cinemaService.findById(id).getScreens();
        Movie movie = null;
        List<SimpMovie> simpMovies = new ArrayList<>();
        for (Screen screen : screens) {
            movie = movieService.findByChineseName(screen.getMovieName());
            if (movie != null) { simpMovies.add(new SimpMovie(movie)); }

            movie = movieService.findByEnglishName(screen.getMovieName());
            if (movie != null) { simpMovies.add(new SimpMovie(movie)); }
        }
        return simpMovies;
    }


}
