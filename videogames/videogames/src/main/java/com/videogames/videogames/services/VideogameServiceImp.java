package com.videogames.videogames.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.videogames.videogames.entity.Videogame;
import com.videogames.videogames.repositories.VideogameRepository;

@Service
public class VideogameServiceImp implements VideogameService{

    @Autowired
    private VideogameRepository videogameRepository;

    @Override
    public Videogame insertVideogame(Videogame videogame) {
        Videogame found = videogameRepository.findByVideogameName(videogame.getVideogameName());
        if(found == null){
            return videogameRepository.save(videogame);
        }
        return null;
    }

    @Override
    public List<Videogame> getAllVideogame() {
        return videogameRepository.findAll();
    }

    @Override
    public Videogame getVideogameById(Videogame videogame) {
        Optional<Videogame> found = videogameRepository.findById(videogame.getId());
        if(found.isPresent()){
            return found.get();
        }else{
            return null;
        }
    }
    
}
