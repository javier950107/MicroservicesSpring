package com.uservideogames.uservideogames.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uservideogames.uservideogames.entity.User;
import com.uservideogames.uservideogames.repositories.UserRespository;

@Service
public class UserServiceImp implements UserService{
    @Autowired
    private UserRespository userRespository;

    @Override
    public User createUser(User user) {
        return userRespository.save(user);
    }

    @Override
    public User getUser(String user) {
        return userRespository.findByUserName(user);
    }

    @Override
    public List<User> findAll() {
        return userRespository.findAll();
    }
    
}
