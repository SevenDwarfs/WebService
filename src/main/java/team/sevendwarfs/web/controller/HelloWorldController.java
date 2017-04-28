package team.sevendwarfs.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team.sevendwarfs.persistence.entities.Movie;
import team.sevendwarfs.persistence.service.MovieService;
import java.util.List;

/**
 * @ClassName: HelloWorldController.
 * @Description:  演示类，返回字符串helloworld，后期不用
 * @author deng
 * @date 2017年4月9日 上午11:05:40
 */
@RestController
public class HelloWorldController {
    @Autowired
    MovieService movieServiceImpl;

    public HelloWorldController() { super(); }

    /**
    * @return String "Hello World" 纯文本
    * @Description 接受 / 下的HTTP路由.
    */
    @RequestMapping("/")
    public String helloWorld() {
        return "Hello World!\n";
    }

//    @RequestMapping("/person")
//    public Person person() {
//        return personService.findOne(1);
//    }

    @RequestMapping("/movie")
    public List<Movie> movieList() {
        return movieServiceImpl.findAll();
    }
}