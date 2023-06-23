package com.uservideogames.uservideogames.services;

import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import com.uservideogames.uservideogames.entities.User;
import com.uservideogames.uservideogames.repositories.UserRepository;
import com.uservideogames.uservideogames.utils.JWTUtil;

@ExtendWith(MockitoExtension.class)
public class UserServicesTest {
    //veridy , doNothing 
    @Mock
    private UserRepository userRepository;
    
    @InjectMocks
    private UserServiceImp userService;


    private User user;

    @BeforeEach
    void setUp(){
        user = new User();
        user.setId(1L);
        user.setUserName("Test");
        user.setEmail("test@gmail.com");
        user.setPassword("12345");

    }

    @DisplayName("Test for save an user")
    @Test
    void saveUserTest(){
        //given
        when(userRepository.findByEmail(user.getEmail())).thenReturn(null);
        when(userRepository.findByUserName(user.getUserName())).thenReturn(null);
        when(userRepository.save(user)).thenReturn(user);
        
        //when
        User savedUser = userService.createUser(user);

        //then
        Assertions.assertThat(savedUser).isEqualTo(savedUser);
    }

    @DisplayName("Validate when repeat a user")
    @Test
    void saveUserTestValidation(){
        //given
        when(userRepository.findByEmail(user.getEmail())).thenReturn(user);
        when(userRepository.findByUserName(user.getUserName())).thenReturn(user);
        
        //when
        User savedUser = userService.createUser(user);

        //then
        Assertions.assertThat(savedUser).isEqualTo(null);
    }

    @DisplayName("Valida find all users")
    @Test
    void findAllUsersTest(){
        //given
        when(userRepository.findAll()).thenReturn(List.of(user));

        //when
        List<User> users = userService.findAllUsers();

        //then
        Assertions.assertThat(users.size()).isEqualTo(1);
    }

    @DisplayName("List a users empty")
    @Test
    void listUsersEmpty(){
        //given
        when(userRepository.findAll()).thenReturn(Collections.emptyList());

        // when
        List<User> users = userService.findAllUsers();

        //then
        Assertions.assertThat(users.size()).isEqualTo(0);
        Assertions.assertThat(users).isEmpty();
    }

    @DisplayName("Test onAuthUser")
    @Test
    void onAuthTest(){
        //given
        when(userRepository.findByUserName(user.getUserName())).thenReturn(user);
        
        //when
        User userFound = userService.onAuthUser(user.getUserName(), user.getPassword());
        
        //then
        Assertions.assertThat(userFound).isEqualTo(user.getUserName());
    }

}


