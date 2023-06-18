package com.videogames.videogames.services;

import static org.mockito.Mockito.when;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.videogames.videogames.entity.Videogame;
import com.videogames.videogames.repositories.VideogameRepository;

@ExtendWith(MockitoExtension.class)
public class VideogameServiceTest {
    @Mock
    private VideogameRepository videogameRepository;

    @InjectMocks
    private VideogameServiceImp videogameServiceImp;

    private Videogame videogame;

    @BeforeEach
    void setUp(){
        videogame = new Videogame(1L, "Zelda");
    }

    @DisplayName("Test when insert a new videogame")
    @Test
    void testInsertVideogame(){
        //given
        when(videogameRepository.findByVideogameName(videogame.getVideogameName())).thenReturn(videogame);

        //when
        Videogame inserted = videogameServiceImp.insertVideogame(videogame);

        //then
        Assertions.assertThat(inserted.getVideogameName()).isEqualTo("Zelda");
    }
}
