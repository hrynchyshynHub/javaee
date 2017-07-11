package com.eleks.repository;

import com.eleks.model.Post;
import com.eleks.model.User;
import com.eleks.validator.UserValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ivan.hrynchyshyn on 10.07.2017.
 */
public class UserRepositoryForDBImpl implements UserRepository{
    private static UserRepositoryForDBImpl userRepositoryForDB;
    private static UserValidator validator = UserValidator.getInstance();
    private static final Logger logger = LoggerFactory.getLogger(UserRepositoryForDBImpl.class);
    private String dbUrl = "jdbc:postgresql://localhost/learndb";
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
            Connection connection = getDbConection();
            String selectQuery = "SELECT *  FROM public.\"User\" where username = ?";
            PreparedStatement statement = connection.prepareStatement(selectQuery);
            statement.setString(1, username);
            System.out.println(statement.toString());
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String dbusername = rs.getString("username");
                String dbpassword = rs.getString("password");
                user = new User(dbusername, dbpassword);
                user.setId(id);
            }
        }catch (Exception e){

            e.printStackTrace();
        }
        System.out.println(user.toString());
        return user;
    }

    @Override
    public boolean addUser(User u) {
        String username = u.getUsername();
        String password = u.getPassword();
        if(validator.userExistsByName(username)){
            System.out.println("User exist by name ");
           return false;
        }else {
            Connection connection = null;
            PreparedStatement preparedStatement = null;
            String insertSql = "INSERT INTO PUBLIC.\"User\"(username, password) VALUES (?,?)";
            try {
                connection = getDbConection();
                preparedStatement = connection.prepareStatement(insertSql);
                preparedStatement.setString(1, username);
                preparedStatement.setString(2, password);
                preparedStatement.executeUpdate();
                return true;
            } catch (SQLException e) {
                logger.info("Error ocured where user was add to database");
                e.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public void deleteUserByName(String usermame) {

    }

    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        String sqlQuery = "SELECT * FROM public.\"User\"";
        try{
            Connection connection = getDbConection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlQuery);
            while (resultSet.next()){
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                users.add(new User(username,password));
            }
            System.out.println(resultSet.toString());
        }catch (SQLException e){
            logger.info("");
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public void addPostToUser(Post p, User u) {
        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;

        java.lang.String insertTableSQL = "INSERT INTO PUBLIC.\"Post\"(description, user_id) VALUES (?,?)";

        try {
            dbConnection = getDbConection();
            preparedStatement = dbConnection.prepareStatement(insertTableSQL);
            preparedStatement.setString(1,p.getDescription());
            preparedStatement.setInt(2,u.getId());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            logger.info(e.getMessage());
        }

    }
    public List<Post> findUserPost(int userId){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        List<Post> posts = new ArrayList<>();
        String sqlQuery = "SELECT * FROM public.\"Post\" WHERE user_id = ? ";
        try{
            connection = getDbConection();
            preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setInt(1,userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            System.out.println(preparedStatement.toString());
            if(resultSet != null){
                while (resultSet.next()){
                    String description = resultSet.getString("description");
                    posts.add(new Post(description));
                }
            }

        }catch (SQLException e){
            logger.info("");
            e.printStackTrace();
        }
        return posts;
    }
    public User findUserWithPosts(String username){
        User user = new User();
        List<Post> posts = new ArrayList<>();
        try{
            Connection connection = getDbConection();
            String selectQuery = "SELECT *  FROM public.\"User\" u left join public.\"Post\" p on u.id = p.user_id WHERE u.username = ?";
            PreparedStatement statement = connection.prepareStatement(selectQuery);
            statement.setString(1, username);
            System.out.println(statement.toString());
            ResultSet rs = statement.executeQuery();
            String dbusername = null;
            String dbpassword = null;
            int dbuserid = 0;
            while (rs.next()) {
                dbuserid = rs.getInt("id");
                dbusername = rs.getString("username");
                dbpassword = rs.getString("password");
                Post post = new Post(rs.getString("description"));
                posts.add(post);
            }
            user.setUsername(username);
            user.setPassword(password);
            user.setId(dbuserid);
            user.setPosts(posts);

        }catch (Exception e){
            e.printStackTrace();
        }
        return user;
    }
    private Connection getDbConection(){
        Connection connection = null;
        try {
            Class.forName(dbClass);
        } catch (ClassNotFoundException e) {
            logger.info("Error during load class!!" + e.getCause());
            e.printStackTrace();
        }

        try {
             connection = DriverManager.getConnection(dbUrl,this.username,this.password);
             return connection;

        } catch (SQLException e){
            e.printStackTrace();
        }
        return connection;
    }
}
