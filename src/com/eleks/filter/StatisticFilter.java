package com.eleks.filter;
import com.eleks.statistics.UrlCollector;
import com.eleks.statistics.UrlObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;
import java.util.concurrent.*;


/**
 * Created by ivan.hrynchyshyn on 06.07.2017.
 */
@WebFilter(servletNames = {
        "UserServlet","LogInServlet","WelcomeServlet"
})
public class StatisticFilter implements Filter{

    private static final Logger logger = LoggerFactory.getLogger(StatisticFilter.class);
    private static UrlCollector urlCollector = UrlCollector.getInstance();


    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String requestUrl = request.getRequestURI();
        urlCollector.addUrl(new UrlObject(requestUrl));
        filterChain.doFilter(request,response);
    }

    public void destroy() {
        doStatistic(LocalDateTime.now());
    }

    public void doStatistic(LocalDateTime localDateTime){
        logger.info("Statistic for:" + localDateTime.toString());
        for(String url:urlCollector.getUniqUrl()){
            logger.info(String.format("url: %s was visited %s", url,urlCollector.getNumberOfVisitedUrl(url)));
        }
    }
}
