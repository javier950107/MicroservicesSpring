package com.uservideogames.uservideogames.services;

import org.springframework.stereotype.Service;

import com.uservideogames.uservideogames.entity.UserVideogames;

@Service
public interface UserVideogameService {
    public UserVideogames insertUserVideogames(UserVideogames userVideogames);
}
