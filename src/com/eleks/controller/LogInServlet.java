package com.eleks.controller;

import com.eleks.model.User;
import com.eleks.repository.UserRepository;
import com.eleks.repository.UserRepositoryImpl;
import com.eleks.validator.UserValidator;
import org.jboss.weld.context.ejb.Ejb;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by ivan.hrynchyshyn on 03.07.2017.
 */
@WebServlet(name = "LogInServlet")
public class LogInServlet extends HttpServlet {

    private UserRepository userRepository  = UserRepositoryImpl.getInstance();
    private UserValidator validator = new UserValidator();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws  IOException, ServletException{
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        System.out.println(username+  password);
     if(validator.checkUser(username,password)) {
         User user = userRepository.findUserByName(username);
         req.setAttribute("user", user);
         RequestDispatcher rd = req.getRequestDispatcher("userProfile.jsp");
         rd.forward(req, resp);
     }else{
         req.getRequestDispatcher("faillogin.jsp").forward(req,resp);
     }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("register.jsp").forward(req,resp);
    }
}
