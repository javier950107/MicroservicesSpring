package com.uservideogames.uservideogames.services;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Date;
import java.util.List;
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

    @DisplayName("Test when show all videogames by user")
    @Test
    void showVideogamesByUser(){
        //given
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(userVideogamesRepository.findByUserId(1L)).thenReturn(List.of(userVideogames));

        //when
        List<UserVideogames> userFound = userVideogamesService.getAllVideogamesByUser(1L);

        //then
        Assertions.assertThat(userFound.get(0).getDescription()).isEqualTo("Description");
        Assertions.assertThat(userFound.size()).isGreaterThan(0);
    }

    @DisplayName("Test when not found user on get all user videogames by id")
    @Test
    void getAllUserVideogamesByUser_ThenReturnUserNotFound(){
        //given
        when(userRepository.findById(1L)).thenReturn(Optional.empty());
        //when(userVideogamesRepository.findByUserId(userVideogames.getUser().getId())).thenReturn(List.of(userVideogames));

        //when
        List<UserVideogames> userFound = userVideogamesService.getAllVideogamesByUser(1L);

        //then
        Assertions.assertThat(userFound).isNull();
    }

    @DisplayName("Test when delete an user videogames")
    @Test
    void deleteUserVideogames(){
        //given
        doNothing().when(userVideogamesRepository).deleteById(userVideogames.getId());

        // when
        userVideogamesService.deleteById(userVideogames);

        //then
        verify(userVideogamesRepository, times(1)).deleteById(userVideogames.getId());
    }

    @DisplayName("Test when update an user videogames")
    @Test
    void updateUserVideogames(){
        //given
        when(userVideogamesRepository.findById(userVideogames.getId())).thenReturn(Optional.of(userVideogames));
        when(userVideogamesRepository.save(userVideogames)).thenReturn(userVideogames);
        userVideogames.setDescription("New Description");

        //when
        UserVideogames updatedUser = userVideogamesService.updateUserVideogames(userVideogames);

        //then
        Assertions.assertThat(updatedUser.getDescription()).isEqualTo("New Description");
    }

    @DisplayName("Test when update a wrong user")
    @Test
    void updateWrongUser_ThenReturnNull(){
        when(userVideogamesRepository.findById(userVideogames.getId())).thenReturn(Optional.empty());

        //when
        UserVideogames updatedUser = userVideogamesService.updateUserVideogames(userVideogames);

        //then
        Assertions.assertThat(updatedUser).isEqualTo(null);
    }


}
