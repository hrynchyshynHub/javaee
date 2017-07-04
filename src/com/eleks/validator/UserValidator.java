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

    private UserRepositoryImpl userRepository = UserRepositoryImpl.getInstance();
    private static  UserValidator userValidator;

    private  UserValidator(){}

    public static UserValidator getInstance(){
        if(userValidator == null){
            synchronized (UserValidator.class){
                if(userValidator == null) userValidator = new UserValidator();
            }
        }
        return  userValidator;
    }

    public boolean checkUser(String username, String password){
        boolean userIsInDatabase = false;

       for(User u: userRepository.findAll()){
           if(u.getUsername().equals(username) && u.getPassword().equals(password)) userIsInDatabase = true;
       }
        return userIsInDatabase;
    }
    public boolean userExistsByName(String username){
        boolean userIsInDatabase = false;

        for(User u:userRepository.findAll()){
            if(u.getUsername().equals(username)) userIsInDatabase = true;
        }
        return userIsInDatabase;
    }

    public void setUserRepository(UserRepositoryImpl userRepository) {
        this.userRepository = userRepository;
    }

    public UserRepositoryImpl getUserRepository() {
        return userRepository;
    }
}
