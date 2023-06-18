package com.videogames.videogames.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.videogames.videogames.entity.Videogame;

public interface VideogameRepository extends JpaRepository<Videogame, Long>{
    public Videogame findByVideogameName(String videogameName);
}
