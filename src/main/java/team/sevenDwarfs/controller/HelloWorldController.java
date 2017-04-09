package team.sevenDwarfs.controller;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/** 
* @ClassName: HelloWorldController 
* @Description:  演示类，返回字符串helloworld，后期不用
* @author deng
* @date 2017年4月9日 上午11:05:40 
*/
@RestController
@EnableAutoConfiguration
@ComponentScan(basePackages="src.main.java")
public class HelloWorldController {
	
	/** 
	* @Description 接受 / 下的HTTP路由
	* @param
	* @return String "Hello World" 纯文本
	* @throws
	*/
	@RequestMapping("/")
	String HelloWorld() {
		return "Hello World!";
	}
}