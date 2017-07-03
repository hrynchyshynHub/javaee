package com.eleks.repository;

import com.eleks.model.User;

import java.util.List;

/**
 * Created by ivan.hrynchyshyn on 03.07.2017.
 */
public interface UserRepository {
    User findUserByName(String username) throws Exception;
    void addUser(User u);
    void deleteUserByName(String usermame) throws Exception;
    List<User> findAll();
}