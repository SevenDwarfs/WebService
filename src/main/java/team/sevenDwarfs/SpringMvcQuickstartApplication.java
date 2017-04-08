package team.sevenDwarfs;
/**
 * 是这整个web应用的入口类，这里保持简单，仅仅设置监听端口
 */


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;


@SpringBootApplication
public class SpringMvcQuickstartApplication implements EmbeddedServletContainerCustomizer {
	/**
	 * 整个web应用的入口点
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(SpringMvcQuickstartApplication.class, args);
	}
	
	/**
	 * 实现 EmbeddedServletContainerCustomizer 接口方法customize，这里可以设置
	 * web应用服务器监听的端口 8080
	 * 如果没有设置，默认监听8080端口
	 */
	public void customize(ConfigurableEmbeddedServletContainer container) {
		container.setPort(8080);
	}
}
