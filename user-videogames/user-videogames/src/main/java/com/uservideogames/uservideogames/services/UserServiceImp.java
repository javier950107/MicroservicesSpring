package com.uservideogames.uservideogames.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uservideogames.uservideogames.entity.User;
import com.uservideogames.uservideogames.repositories.UserRepository;

@Service
public class UserServiceImp implements UserService{
    @Autowired
    private UserRepository userRepository;

    @Override
    public User createUser(User user) {
        User userFoundByName = userRepository.findByUserName(user.getUserName());
        User userFoundByEmail = userRepository.findByEmail(user.getEmail());

        if (userFoundByName != null || userFoundByEmail != null){
            return null;
        }
        return userRepository.save(user);
    }

    @Override
    public User getUser(String user) {
        return userRepository.findByUserName(user);
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public boolean onAuthUser(String userName, String password){
        User userFound = userRepository.findByUserName(userName);

        if(userFound != null){
            if(userFound.getPassword().equals(password)){
                return true;
            }else{
                return false;
            }
        }else{
            return false;
        }
    }
    
}
