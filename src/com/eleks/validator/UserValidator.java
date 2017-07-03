package com.eleks.validator;

import com.eleks.model.User;
import com.eleks.repository.UserRepository;
import com.eleks.repository.UserRepositoryImpl;

import javax.inject.Inject;
import javax.inject.Qualifier;

/**
 * Created by ivan.hrynchyshyn on 03.07.2017.
 */
public class UserValidator {
    private UserRepository userRepository = UserRepositoryImpl.getInstance();

    public boolean checkUser(String username, String password){
        boolean userIsInDatabase = false;

       for(User u:userRepository.findAll()){
           if(u.getUsername().equals(username) && u.getPassword().equals(password)) userIsInDatabase = true;
       }
        return userIsInDatabase;
    }
}
