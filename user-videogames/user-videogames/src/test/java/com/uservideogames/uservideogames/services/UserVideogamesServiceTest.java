package com.uservideogames.uservideogames.services;

import static org.mockito.Mockito.when;

import java.util.Date;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.uservideogames.uservideogames.entity.User;
import com.uservideogames.uservideogames.entity.UserVideogames;
import com.uservideogames.uservideogames.repositories.UserRepository;
import com.uservideogames.uservideogames.repositories.UserVideogamesRepository;

@ExtendWith(MockitoExtension.class)
public class UserVideogamesServiceTest {
    
    @Mock
    UserVideogamesRepository userVideogamesRepository;

    @Mock
    UserRepository userRepository;

    @InjectMocks
    UserVideogamesServiceImp userVideogamesService;

    private User user;
    private UserVideogames userVideogames;

    @BeforeEach
    void setUp(){
        user = new User();
        user.setId(1L);
        user.setUserName("Test");
        user.setEmail("test@gmail.com");
        user.setPassword("12345");

        userVideogames = new UserVideogames();
        userVideogames.setDescription("Description");
        userVideogames.setEndDate(new Date(System.currentTimeMillis()));
        userVideogames.setGameId(1L);
        userVideogames.setGrade(1);
        userVideogames.setPlatform("Switch");
        userVideogames.setUser(user);
    }

    @DisplayName("Test insert new user videogames service")
    @Test
    void whenCreateANewUserVideogame_ThenReturnTheUserVideogames(){
        //given
        when(userRepository.findById(userVideogames.getUser().getId())).thenReturn(Optional.of(user));
        when(userVideogamesRepository.save(userVideogames)).thenReturn(userVideogames);
        //when

        UserVideogames userInserted = userVideogamesService.insertUserVideogames(userVideogames);
        //then

        Assertions.assertThat(userInserted).isEqualTo(userVideogames);
        Assertions.assertThat(userInserted.getUser().getId()).isEqualTo(1L);
    }

    @DisplayName("Test when the user doesn't exists")
    @Test
    void whenTheUserDontExists_ThenReturnNull(){
        //given
        when(userRepository.findById(userVideogames.getUser().getId())).thenReturn(Optional.empty());
       
        //when
        UserVideogames userInserted = userVideogamesService.insertUserVideogames(userVideogames);

        //then
        Assertions.assertThat(userInserted).isEqualTo(null);
    }


}
