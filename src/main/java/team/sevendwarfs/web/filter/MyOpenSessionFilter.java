package team.sevendwarfs.web.filter;

import org.springframework.core.annotation.Order;
import org.springframework.orm.hibernate5.support.OpenSessionInViewFilter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * Created by deng on 2017/4/26.
 */

@WebFilter(filterName="myFilter", urlPatterns="/*")
@Order(5)
public class MyOpenSessionFilter implements Filter {
    private final OpenSessionInViewFilter filter;


    public MyOpenSessionFilter() {
        filter = new OpenSessionInViewFilter();
    }


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        filter.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        filter.doFilter(request, response, chain);
    }

    @Override
    public void destroy() {
        filter.destroy();
    }
}
