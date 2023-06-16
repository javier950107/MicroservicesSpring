package com.uservideogames.uservideogames.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uservideogames.uservideogames.entity.UserVideogames;
import com.uservideogames.uservideogames.repositories.UserVideogamesRepository;

@Service
public class UserVideogamesServiceImp implements UserVideogameService{

    @Autowired
    UserVideogamesRepository userVideogameRepository;

    @Override
    public UserVideogames insertUserVideogames(UserVideogames userVideogames) {
        return null;
    }
    
}
