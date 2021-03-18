package com.dealership.repository;

import com.dealership.model.User;
import com.dealership.utility.ConnectionClosures;
import com.dealership.utility.ConnectionFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepositoryImp implements UserRepository{

    private static final Logger LOG = LogManager.getFormatterLogger(UserRepositoryImp.class);

    public static Connection conn = null;
    public static Statement stmt;
    public static ResultSet set;

    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        final String SQL = "select * from users";
        try{
            conn=ConnectionFactory.getConnection();
            stmt= conn.createStatement();
            set = stmt.executeQuery(SQL);

            while(set.next()){
                users.add(new User(
                        set.getInt(1),
                        set.getString(2),
                        set.getString(3),
                        set.getString(4),
                        set.getString(5),
                        set.getString(6),
                        set.getString(7),
                        set.getInt( 8)
                ));
            }

        }catch (SQLException e){
            LOG.trace(e);
        }finally{
            ConnectionClosures.close(conn);
            ConnectionClosures.close(stmt);
            ConnectionClosures.close(set);
        }

        return users;
    }

    @Override
    public User findById(int userId) {
        return null;
    }

    @Override
    public User findByDriversLicense(String license) {
        User user = null;

        Connection conn = null;
        PreparedStatement stmt= null;
        ResultSet set = null;
        final String SQL = "select * from user where user_dl_number = ?";

        try{
            conn = ConnectionFactory.getConnection();
            stmt = conn.prepareStatement(SQL);
            stmt.setString(1, license);
            set = stmt.executeQuery();

            while (set.next()){
                user = new User (set.getInt(1),
                        set.getString(2),
                        set.getString(3),
                        set.getString(4),
                        set.getString(5),
                        set.getString(6),
                        set.getString(7),
                        set.getInt(8));
            }

        }catch (SQLException e){
            e.printStackTrace();
        }finally{
            ConnectionClosures.close(conn);
            ConnectionClosures.close(stmt);
            ConnectionClosures.close(set);
        }
        return user;
    }

    @Override
    public User findByUsername(String username) {
        User user = null;
        PreparedStatement stmt = null;
        ResultSet set = null;
        final String SQL = "select * from users where user_username = ?";
        try{
            conn =  ConnectionFactory.getConnection();
            stmt = conn.prepareStatement(SQL);
            stmt.setString(1,username);
            set =  stmt.executeQuery();

            while(set.next()){
                user = new User( set.getInt(1),
                        set.getString(2),
                        set.getString(3),
                        set.getString(4),
                        set.getString(5),
                        set.getString(6),
                        set.getString(7),
                        set.getInt(8));
            }

        }catch (SQLException e){
            e.printStackTrace();
        } finally{
            ConnectionClosures.close(conn);
            ConnectionClosures.close(stmt);
            ConnectionClosures.close(set);
        }

        return user;
    }

    @Override
    public void addUser(User user) {

        PreparedStatement stmt = null;
        final String SQL = "insert into users values(default, ?, ?, ?, ?, ?, ?, 1 )"; //Parameterized statement to prevent SQL injection

        try{
            conn = ConnectionFactory.getConnection();
            stmt = conn.prepareStatement(SQL);

            stmt.setString( 1,user.getUsername());
            stmt.setString(2,user.getPassword());
            stmt.setString(3,user.getFirstName());
            stmt.setString(4,user.getLastName());
            stmt.setString(5,user.getDlNumber());
            stmt.setString(6,user.getEmail());

            stmt.execute();

        }catch (SQLException e){
            e.printStackTrace();
        } finally{
            ConnectionClosures.close(conn);
            ConnectionClosures.close(stmt);
        }

    }

    @Override
    public void removeUser(User user) {

    }
}
