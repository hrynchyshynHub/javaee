package com.eleks.controller;

import com.eleks.model.Post;
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
@WebServlet(name = "UserServlet")
public class UserServlet extends HttpServlet {

    private UserRepository userRepository = UserRepositoryImpl.getInstance();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String desc = request.getParameter("postDescription");
        User user = (User)request.getSession(false).getAttribute("user");
        System.out.println(user + "user servlet");
        Post post = new Post(desc);
        userRepository.addPostToUser(post,user);
        request.getRequestDispatcher("userProfile.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
