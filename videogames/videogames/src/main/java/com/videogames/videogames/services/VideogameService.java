package com.videogames.videogames.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.videogames.videogames.entity.Videogame;

@Service
public interface VideogameService {
    public Videogame insertVideogame(Videogame videogame);
    public List<Videogame> getAllVideogame();
    public Videogame getVideogameById(Videogame videogame);
}