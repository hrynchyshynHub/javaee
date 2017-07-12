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
    private static final Logger logger = LoggerFactory.getLogger(UserRepositoryForDBImpl.class);

    private static String selectUserByNameQuery = "SELECT *  FROM PUBLIC.\"User\" WHERE USERNAME = ?";
    private static String insertUserQuery = "INSERT INTO PUBLIC.\"User\"(USERNAME, PASSWORD) VALUES (?,?)";
    private static String selectAllUsersQuery = "SELECT * FROM PUBLIC.\"User\"";
    private static String insertPostQuery = "INSERT INTO PUBLIC.\"Post\"(DESCRIPTION, USER_ID) VALUES (?,?)";
    private static String selectPostForUserQuery = "SELECT * FROM PUBLIC.\"Post\" WHERE USER_ID = ? ";
    private static String selectUserWithPostQuery = "SELECT *  FROM public.\"User\" u left join public.\"Post\" p on u.id = p.user_id WHERE u.username = ?";

    private String dbUrl = "jdbc:postgresql://localhost/learndb";
    private String dbClass = "org.postgresql.Driver";
    private String username = "postgres";
    private String password = "root";

    private UserRepositoryForDBImpl(){

    }

    public static UserRepositoryForDBImpl getInstance(){
        if(userRepositoryForDB == null){
            synchronized (UserRepositoryForDBImpl.class){
                if(userRepositoryForDB == null) userRepositoryForDB = new UserRepositoryForDBImpl();
            }
        }
        return userRepositoryForDB;
    }

    @Override
    public User findUserByName(String username) throws Exception {

        User user = null;
        try(Connection connection = getDbConection(); PreparedStatement statement = connection.prepareStatement(selectUserByNameQuery)){
            statement.setString(1, username);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String dbusername = rs.getString("username");
                String dbpassword = rs.getString("password");
                user = new User(dbusername, dbpassword);
                user.setId(id);
            }
        }catch (Exception e){
            logger.info("Problem with connection  "  + e );
        }
        if(user!= null){
            return user;
        }else throw new Exception("user not found");

    }

    @Override
    public boolean addUser(User u) {
        String username = u.getUsername();
        String password = u.getPassword();
        try( Connection connection = getDbConection(); PreparedStatement preparedStatement = connection.prepareStatement(insertUserQuery))
            {
                preparedStatement.setString(1, username);
                preparedStatement.setString(2, password);
                preparedStatement.executeUpdate();
                return true;
            } catch (SQLException e) {
                logger.info("Error ocured where user was add to database" + e);
            }
        return false;
        }

    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        try(Connection connection = getDbConection();Statement statement = connection.createStatement()){
            ResultSet resultSet = statement.executeQuery(selectAllUsersQuery);
            while (resultSet.next()){
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                users.add(new User(username,password));
            }
        }catch (SQLException e){
            logger.info("Problem occured by database connection " + e);
        }
        return users;
    }

    @Override
    public void addPostToUser(Post p, User u) {
        try (Connection dbConnection = getDbConection();PreparedStatement preparedStatement = dbConnection.prepareStatement(insertPostQuery)){
            preparedStatement.setString(1,p.getDescription());
            preparedStatement.setInt(2,u.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.info("Error during connected to database " + e );
        }
    }

    public List<Post> findUserPost(int userId){

        List<Post> posts = new ArrayList<>();

        try(Connection connection = getDbConection();PreparedStatement preparedStatement = connection.prepareStatement(selectPostForUserQuery)){
            preparedStatement.setInt(1,userId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                String description = resultSet.getString("description");
                posts.add(new Post(description));
            }
        }catch (SQLException e){
            logger.info("Error during connected to database " + e );
        }
        return posts;
    }

    public User findUserWithPosts(String username){
        User user = new User();
        List<Post> posts = new ArrayList<>();
        try(Connection connection = getDbConection(); PreparedStatement statement = connection.prepareStatement(selectUserWithPostQuery)){
            statement.setString(1, username);
            System.out.println(statement.toString());
            ResultSet rs = statement.executeQuery();
            int dbuserid = rs.getInt("id");
            String dbusername = rs.getString("username");
            String dbpassword = rs.getString("password");

            while (rs.next()) {
                Post post = new Post(rs.getString("description"));
                posts.add(post);
            }

            user.setUsername(dbusername);
            user.setPassword(dbpassword);
            user.setId(dbuserid);
            user.setPosts(posts);

        }catch (Exception e){
            logger.info("Error during connected to database " + e );
        }
        return user;
    }

    private Connection getDbConection(){
        Connection connection = null;
        try {
            Class.forName(dbClass);
             connection = DriverManager.getConnection(dbUrl,this.username,this.password);
             return connection;
        } catch (SQLException e){
            logger.info("Error during connected to database " + e );
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }



    @Override
    public void deleteUserByName(String usermame) {

    }
}
