package com.uservideogames.uservideogames.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.uservideogames.uservideogames.entity.User;

@Service
public interface UserService {
    
    public User createUser(User user);
    public boolean onAuthUser(String user, String password);
    public User getUser(String user);
    public List<User> findAllUsers();
    //public void createVideogameUser(Videogame videogame);
}
