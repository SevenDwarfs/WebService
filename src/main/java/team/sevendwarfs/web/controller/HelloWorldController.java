package team.sevendwarfs.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team.sevendwarfs.persistence.service.PersonService;

/**
 * @ClassName: HelloWorldController.
 * @Description:  演示类，返回字符串helloworld，后期不用
 * @author deng
 * @date 2017年4月9日 上午11:05:40
 */
@RestController
//@EnableAutoConfiguration
public class HelloWorldController {
    @Autowired
    PersonService personService;

    /**
    * @return String "Hello World" 纯文本
    * @Description 接受 / 下的HTTP路由.
    */
    @RequestMapping("/")
    public String helloWorld() {
        return "Hello World!\n";
  }

    @RequestMapping("/test")
    public String person() {
        return personService.findAll().toString();
    }
}