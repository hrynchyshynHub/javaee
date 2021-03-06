package com.eleks.service;

import com.eleks.model.Post;
import com.eleks.model.User;
import com.eleks.repository.UserRepository;
import java.util.List;

/**
 * Created by ivan.hrynchyshyn on 12.07.2017.
 */
public class UserService {
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean saveUser(User u){
        if(userPresentInDataBase(u.getUsername(),u.getPassword())){
            return false;
        }else {
           userRepository.addUser(u);
           return true;
        }
    }
    public List<User> findAll(){
        return userRepository.findAll();
    }
    public User findUserByUsername(String username) throws Exception {
       return userRepository.findUserByName(username);
    }

    public  List<Post> findPostForUser(int userId){
        return null;
    }

    public User findUserWithPosts(String username) throws Exception{
        User user = userRepository.findUserByName(username);
        if(user!= null){
            List<Post> posts = userRepository.findUserPosts(user.getId());
            user.setPosts(posts);
        }else throw new Exception("user not found");

        return user;
    }

    public void addPostToUser(User u, Post p) throws Exception {
        if(p.getDescription().length()>0){
            userRepository.addPostToUser(p,u);
        }else throw new Exception("empty field");
    }

    public boolean userPresentInDataBase(String username, String password){
        boolean userAlredyExist = false;
        for(User u:findAll()){
            if(u.getUsername().equals(username) && u.getPassword().equals(password)) {
                userAlredyExist = true;
                break;
            }
        }
        return userAlredyExist;
    }

}
