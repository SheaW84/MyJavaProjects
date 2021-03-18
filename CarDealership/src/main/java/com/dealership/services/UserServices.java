package com.dealership.services;

import com.dealership.model.User;
import com.dealership.repository.UserRepository;
import com.dealership.repository.UserRepositoryImp;
import com.dealership.utility.DuplicateDriverException;

import java.util.List;

public class UserServices {

    private UserRepository userRepository;

    public UserServices(){this.userRepository = new UserRepositoryImp();}

    public List<User> findAll(){
     return this.userRepository.findAll();
    }

    public User findById(int userId){
        return this.userRepository.findById(userId);
    }

    public User findByUsername(String username){
        return this.userRepository.findByUsername(username);
    }

    public User findByDriversLicense(String dlNumber){

        return this.userRepository.findByDriversLicense(dlNumber);
    }

    public void addUser(User user){

            this.userRepository.addUser(user);

    }

    public void removeUser(User user){
        this.userRepository.removeUser(user);
    }


}
