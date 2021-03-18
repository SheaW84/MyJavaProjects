package com.dealership.repository;

import com.dealership.model.User;

import java.util.List;

public interface UserRepository {

    List<User> findAll();

    User findById(int userId);

    User findByDriversLicense(String license);

    User findByUsername(String username);

    void addUser(User user);

    void removeUser(User user);

}
