package team.sevendwarfs.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import team.sevendwarfs.common.Util;
import team.sevendwarfs.persistence.entities.Movie;
import team.sevendwarfs.persistence.service.MovieService;
import team.sevendwarfs.web.model.SimpMovie;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by deng on 2017/6/20.
 */

@Controller
@RequestMapping("/api")
public class SearchController {
    @Autowired
    MovieService movieService;


    @GetMapping("/search")
    @ResponseBody
    public List<SimpMovie> searchMovie(@RequestParam(value = "query")
                                       String query) {
        if ("".equals(query)) { return new ArrayList<SimpMovie>(); }

        List<Movie> movieList = movieService.findByNameContaining(query);

        return Util.MoviesToSimpMovies(movieList);
    }

}
