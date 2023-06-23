package com.uservideogames.uservideogames.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uservideogames.uservideogames.entities.User;
import com.uservideogames.uservideogames.repositories.UserRepository;
import com.uservideogames.uservideogames.utils.JWTUtil;

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
    public User onAuthUser(String userName, String password){
        User userFound = userRepository.findByUserName(userName);

        if(userFound != null){
            if(userFound.getPassword().equals(password)){
                return userFound;
            }else{
                return null;
            }
        }else{
            return null;
        }
    }
    
}
