package com.eleks.controller;

import com.eleks.model.User;
import com.eleks.repository.UserRepository;
import com.eleks.repository.UserRepositoryImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by ivan.hrynchyshyn on 04.07.2017.
 */
@WebServlet(name = "RegisterServlet")
public class RegisterServlet extends HttpServlet {

    UserRepository userRepository = UserRepositoryImpl.getInstance();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        User user = new User(username,password);
        if(userRepository.addUser(user)){
            request.getRequestDispatcher("welcome.jsp").forward(request,response);
        }else{
            request.setAttribute("error", new Exception("User Alredy Exist, please use another nickname"));
            request.getRequestDispatcher("welcome.jsp").forward(request,response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
