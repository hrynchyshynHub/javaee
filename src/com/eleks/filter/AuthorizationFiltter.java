package com.eleks.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * Created by ivan.hrynchyshyn on 06.07.2017.
 */
@WebFilter(urlPatterns = {"/userProfile.jsp","/addPostToUser"},
            servletNames = "UserServlet")
public class AuthorizationFiltter implements Filter{

    public void init(FilterConfig filterConfig) throws ServletException {
    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest  request = (HttpServletRequest) servletRequest;
        HttpServletResponse response= (HttpServletResponse) servletResponse;
        String url = request.getRequestURI();

            Object o = request.getSession().getAttribute("user");
            if (null == o) {
                request.setAttribute("error", new Exception("Please authorificate first!"));
                request.getRequestDispatcher("welcome.jsp").forward(request,response);

            }else{
                filterChain.doFilter(request, response);
            }
    }

    public void destroy() {
    }
}
