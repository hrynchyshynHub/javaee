package com.eleks.controller;

import com.eleks.model.Post;
import com.eleks.model.User;
import com.eleks.repository.UserRepository;
import com.eleks.repository.UserRepositoryForDBImpl;
import com.eleks.repository.UserRepositoryImpl;
import com.eleks.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by ivan.hrynchyshyn on 04.07.2017.
 */
@WebServlet(name = "UserServlet", urlPatterns = {"/addPostToUser"})
public class UserServlet extends HttpServlet {

    private UserService userService = new UserService(UserRepositoryForDBImpl.getInstance());

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String desc = request.getParameter("postDescription");
        User user = (User)request.getSession(false).getAttribute("user");
        Post post = new Post(desc);
        try {
                userService.addPostToUser(user,post);
                user.getPosts().add(post);
                request.getRequestDispatcher("userProfile.jsp").forward(request,response);
            } catch (Exception e) {
                request.setAttribute("error", e);
                request.getRequestDispatcher("userProfile.jsp").forward(request,response);
            }
        }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }


}
