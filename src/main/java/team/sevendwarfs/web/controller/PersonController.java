package team.sevendwarfs.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import team.sevendwarfs.persistence.entities.Movie;
import team.sevendwarfs.persistence.entities.Person;
import team.sevendwarfs.persistence.service.MovieService;
import team.sevendwarfs.persistence.service.PersonService;

import java.util.List;

/**
 * Created by deng on 2017/5/6.
 */

@Controller
@RequestMapping("/api/person")
public class PersonController {
    @Autowired
    PersonService personService;

    @Autowired
    MovieService movieService;

    /**
     * 根据演员的ID返回演员信息
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    @ResponseBody
    public Person getPersonById(@PathVariable("id") Integer id) {
        return personService.findById(id);
    }

    /**
     * 返回电影参演的演员和导演信息
     * @param id
     * @return
     */
    @GetMapping("/movie/{id}")
    @ResponseBody
    public List<Person> getPersonByMovie(@PathVariable("id") Integer id) {
        Movie movie = movieService.findById(id);
        List<Person> list = personService.findByMovie(movie);
        return list;
    }
}
