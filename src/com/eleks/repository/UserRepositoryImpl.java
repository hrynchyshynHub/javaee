package com.eleks.repository;

import com.eleks.model.Post;
import com.eleks.model.User;
import com.eleks.validator.UserValidator;

import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * Created by ivan.hrynchyshyn on 03.07.2017.
 */
@ApplicationScoped
public class UserRepositoryImpl implements UserRepository {
    private static UserRepositoryImpl userRepository;
    private  List<User> users = new ArrayList<User>();


   private UserRepositoryImpl(){
        User user = new User("Ivan", "1111");
        user.setPosts(Arrays.asList(new Post("asdasd"), new Post("dasasd")));
        users.add(user);
        users.add(new User("Pavlo", "1111"));
        users.add(new User("Maks", "1111"));
        users.add(new User("Andriy", "1111"));
        users.add(new User("Oleg", "1111"));
        users.add(new User("default", "1111"));
    }

    public static UserRepositoryImpl getInstance(){
       if(userRepository == null) userRepository = new UserRepositoryImpl();
       return userRepository;
    }

    @Override
    public User findUserByName(String name) {
        User u = null;
        for (User user : users){
            if(user.getUsername().equals(name))
                u = user;
        }
        if(u == null) System.out.println("User not found");
        System.out.println(u + " user ");
        return u;

    }

    @Override
    public boolean addUser(User u) {
        for (User user:users) {
            if(user.getUsername().equals(u.getUsername())) return false;
        }
        users.add(u);
        return true;
    }

    @Override
    public void deleteUserByName(String usermame){
        Iterator<User> iter = users.iterator();
        while (iter.hasNext()){
            User u = iter.next();
            if(u.getUsername().equals(usermame)){
                iter.remove();
            }
        }
    }

    @Override
    public List<User> findAll() {
        return  this.users;
    }

    @Override
    public void addPostToUser(Post p, User u) {
        ArrayList <Post>list = new ArrayList<Post>();
        list.addAll(u.getPosts());
        list.add(p);
        u.setPosts(list);
    }
}