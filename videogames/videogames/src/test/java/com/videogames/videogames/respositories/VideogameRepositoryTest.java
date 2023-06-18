package com.videogames.videogames.respositories;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.videogames.videogames.entity.Videogame;
import com.videogames.videogames.repositories.VideogameRepository;

@DataJpaTest
public class VideogameRepositoryTest {
    @Autowired
    private VideogameRepository videogameRepository;
    
    private Videogame videogame;

    @BeforeEach
    void setUp(){
        videogame = new Videogame(1L, "Zelda");
    }

    @DisplayName("Test repository when insert")
    @Test
    void repositoryInsert(){
        videogameRepository.save(videogame);
        Videogame found = videogameRepository.findByVideogameName(videogame.getVideogameName());

        Assertions.assertThat( found.getVideogameName()).isEqualTo("Zelda");
    }
}
