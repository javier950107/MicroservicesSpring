package com.uservideogames.uservideogames.repositories;

import java.util.Date;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.uservideogames.uservideogames.entity.User;
import com.uservideogames.uservideogames.entity.UserVideogames;

@DataJpaTest
public class UserVideogamesRepositoryTest {
    
    @Autowired
    private UserVideogamesRepository userVideogamesRepository;

    @Autowired
    private UserRepository userRepository;

    private User user;
    private UserVideogames userVideogames;

    @BeforeEach
    void setUp(){
        user = new User();
        user.setId(1L);
        user.setUserName("Test");
        user.setEmail("test@gmail.com");
        user.setPassword("12345");

        User userInserted = userRepository.save(user);
        userVideogames = new UserVideogames();
        userVideogames.setDescription("Description");
        userVideogames.setEndDate(new Date(System.currentTimeMillis()));
        userVideogames.setGameId(1L);
        userVideogames.setGrade(1);
        userVideogames.setId(1L);
        userVideogames.setPlatform("Switch");
        userVideogames.setUser(userInserted);
    }

    @DisplayName("Testing insert a new user with videogames")
    @Test
    void whenInsertNewUserVideogames_ThenReturnUserVideogames(){
        //given
        //when
        UserVideogames userInserted = userVideogamesRepository.save(userVideogames);

        //then
        Assertions.assertThat(userInserted.getDescription()).isEqualTo("Description");
    }

}
