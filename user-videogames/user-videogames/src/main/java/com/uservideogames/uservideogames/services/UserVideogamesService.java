package com.uservideogames.uservideogames.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.uservideogames.uservideogames.entity.UserVideogames;

@Service
public interface UserVideogamesService {
    public UserVideogames insertUserVideogames(UserVideogames userVideogames);
    public List<UserVideogames> getAllVideogamesByUser(Long id);
    public void deleteById(UserVideogames userVideogames);
    public UserVideogames updateUserVideogames(UserVideogames userVideogames);
}
