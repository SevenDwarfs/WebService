package team.sevendwarfs;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.context.annotation.ComponentScan;

/**
* @ClassName: SpringMvcQuickstartApplication.
* exclude: 关闭 SpringBoot的DataSource和Hibernate自动化配置, 使用显示配置
* @Description: 是这整个web应用的入口类，并以这个文件所在包为根扫描所有包下所有注解，这里保持简单，仅仅设置监听端口
* @author deng
* @date 2017年4月9日 上午11:06:07
*/
@SpringBootApplication
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class,
                                    HibernateJpaAutoConfiguration.class})
@ComponentScan("classpath:*")
public class SpringMvcQuickstartApplication implements EmbeddedServletContainerCustomizer {

  /**
   * @Description 整个web应用的入口点.
   */
  public static void main(String[] args) {
    SpringApplication.run(SpringMvcQuickstartApplication.class, args);
  }

  /**
  * @Title: customize.
  * @Description: 实现 EmbeddedServletContainerCustomizer 接口方法customize，这里可以设置
  *         web应用服务器监听的端口 8080
  * @param container 我也不知道是什么
  */
  public void customize(ConfigurableEmbeddedServletContainer container) {
    container.setPort(8080);
  }
}
