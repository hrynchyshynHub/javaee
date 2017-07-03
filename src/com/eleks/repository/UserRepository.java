package com.eleks.repository;

import com.eleks.model.User;

import java.util.List;

/**
 * Created by ivan.hrynchyshyn on 03.07.2017.
 */
public interface UserRepository {
    User findUserByName(String username);
    void addUser(User u);
    void deleteUserByName(String usermame);
    List<User> findAll();
}