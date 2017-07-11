package com.eleks.repository;

import com.eleks.model.Post;
import com.eleks.model.User;

import java.sql.*;
import java.util.List;

/**
 * Created by ivan.hrynchyshyn on 10.07.2017.
 */
public class UserRepositoryForDBImpl implements UserRepository{
    private static UserRepositoryForDBImpl userRepositoryForDB;
    private String dbUrl = "\"jdbc:postgresql://localhost:5432/learndb\"";
    private String dbClass = "org.postgresql.Driver";
    private String username = "postgres";
    private String password = "root";

    private UserRepositoryForDBImpl(){}

    public static UserRepositoryForDBImpl getInstance(){
        if(userRepositoryForDB == null){
            synchronized (UserRepositoryForDBImpl.class){
                if(userRepositoryForDB == null) userRepositoryForDB = new UserRepositoryForDBImpl();
            }
        }
        return userRepositoryForDB;
    }

    @Override
    public User findUserByName(String username){

        User user = null;
        try{
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost/learndb",this.username,this.password);
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select *  FROM public.\"User\" ;");

            while (rs.next()){
                int id = rs.getInt("id");
                String dbusername = rs.getString("username");
                String dbpassword = rs.getString("password");
                user = new User(dbusername,dbpassword);
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public boolean addUser(User u) {
        return false;
    }

    @Override
    public void deleteUserByName(String usermame) {

    }

    @Override
    public List<User> findAll() {
        String query = "Select * FROM user";
        return null;
    }

    @Override
    public void addPostToUser(Post p, User u) {

    }
}
