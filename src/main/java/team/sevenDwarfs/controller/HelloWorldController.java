package team.sevenDwarfs.controller;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 演示类，返回字符串helloworld，后期不用
 * @RestController 表示这一个是返回字符串的控制器
 * @EnableAutoConfiguration 使用Spring-boot的自动配置
 * @author deng
 *
 */
@RestController
@EnableAutoConfiguration
@ComponentScan(basePackages="src.main.java")
public class HelloWorldController {
	/**
	 * 接受 / 下的HTTP路由
	 * @return "Hello World" 纯文本
	 */
	@RequestMapping("/")
	String HelloWorld() {
		return "Hello World!";
	}
}