package com.uservideogames.uservideogames;

import static org.mockito.Mockito.when;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import com.uservideogames.uservideogames.entity.User;
import com.uservideogames.uservideogames.repositories.UserRespository;
import com.uservideogames.uservideogames.services.UserService;


@ExtendWith(MockitoExtension.class)
public class UserRepositoryMockTest {
    
    @Mock
    private UserService userService;

    private User user;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
        
        user = new User();
        user.setUserName("Isco");
        user.setPassword("12345");
        user.setEmail("jijija@gmail.com");
    
    }

    @Test
    public void whenFindByUser_TheReturnAUser(){
        when(userService.getUser("Isco")).thenReturn(user);

        Assertions.assertThat(userService.getUser("Isco")).isEqualTo(user);
        Assertions.assertThat(userService.getUser("")).isEqualTo(null);
    }


    @Test
    public void whenFindAll_ThenReturnAList(){
        when(userService.findAll()).thenReturn(List.of(user));

        Assertions.assertThat(userService.findAll().size()).isGreaterThan(0);
        Assertions.assertThat(userService.findAll()).isEqualTo(List.of(user));
    }
}
