package team.sevendwarfs.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team.sevendwarfs.persistence.entities.Movie;
import team.sevendwarfs.persistence.entities.Person;
import team.sevendwarfs.persistence.service.MovieService;
import team.sevendwarfs.persistence.service.PersonService;

import java.util.List;

/**
 * @ClassName: HelloWorldController.
 * @Description:  演示类，返回字符串helloworld，后期不用
 * @author deng
 * @date 2017年4月9日 上午11:05:40
 */
@Controller
public class HelloWorldController {
    @Autowired
    PersonService personService;

    @Autowired
    MovieService movieService;

    public HelloWorldController() { super(); }

    /**
    * @return String "Hello World" 纯文本
    * @Description 接受 / 下的HTTP路由.
    */
    @RequestMapping("/")
    public String helloWorld() {
        return "Hello World!\n";
    }

    @RequestMapping("/person")
    public Person person() {
        return personService.findOne(1);
    }
}