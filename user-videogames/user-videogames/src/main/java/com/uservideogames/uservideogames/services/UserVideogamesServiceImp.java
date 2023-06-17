package com.uservideogames.uservideogames.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uservideogames.uservideogames.entity.User;
import com.uservideogames.uservideogames.entity.UserVideogames;
import com.uservideogames.uservideogames.repositories.UserRepository;
import com.uservideogames.uservideogames.repositories.UserVideogamesRepository;

@Service
public class UserVideogamesServiceImp implements UserVideogamesService{

    @Autowired
    UserVideogamesRepository userVideogameRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public UserVideogames insertUserVideogames(UserVideogames userVideogames) {
        Optional<User> userFound = userRepository.findById(userVideogames.getUser().getId());
        if(userFound.isPresent()){
            return userVideogameRepository.save(userVideogames);    
        }else{
            return null;
        }
    }
    
}
