package com.eleks.repository;

import com.eleks.model.User;

import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
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
        users.add(new User("Ivan", "1111"));
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
    public void addUser(User u) {
        users.add(u);
        System.out.println("user " + u + " was add");
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


}