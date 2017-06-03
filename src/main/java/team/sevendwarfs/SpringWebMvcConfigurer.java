package team.sevendwarfs;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import team.sevendwarfs.web.interceptor.MyInterceptor;

/**
 * Created by deng on 2017/4/26.
 */
@Configuration
public class SpringWebMvcConfigurer extends WebMvcConfigurerAdapter {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 添加拦截规则
        // 只有登录过后才能访问 user/目录下资源
        registry.addInterceptor(new MyInterceptor()).addPathPatterns
                ("/api/user/**");
        super.addInterceptors(registry);
    }
}
