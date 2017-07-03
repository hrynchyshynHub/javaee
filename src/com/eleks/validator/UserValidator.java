package com.eleks.validator;

import com.eleks.model.User;
import com.eleks.repository.UserRepository;

import javax.inject.Inject;

/**
 * Created by ivan.hrynchyshyn on 03.07.2017.
 */
public class UserValidator {
    @Inject
    private UserRepository userRepository;

    public boolean checkUser(String username, String password){
        User user = null;
        try {
            user = userRepository.findUserByName(username);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(user);
        if(user.getPassword().equals(password)) return true;
        return false;
    }
}
