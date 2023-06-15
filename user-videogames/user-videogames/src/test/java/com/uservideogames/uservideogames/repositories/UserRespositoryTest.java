package com.uservideogames.uservideogames.repositories;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.uservideogames.uservideogames.entity.User;

//import org.mockito.BDDMockito.*;

@DataJpaTest
public class UserRespositoryTest {
    
    @Autowired
    private UserRepository userRepository;

    private User user;

    @BeforeEach
    void setUp(){
        user = new User();
        user.setId(1L);
        user.setUserName("Test");
        user.setEmail("test@gmail.com");
        user.setPassword("12345");

    }

    @Test
    void testSaveUser(){
        //given
        User user1 = new User();
        user1.setId(1L);
        user1.setUserName("Test");
        user1.setEmail("test@gmail.com");
        user1.setPassword("12345");

        //when
        User savedUser = userRepository.save(user1);

        //then
        Assertions.assertThat(savedUser).isNotNull();
        Assertions.assertThat(savedUser.getEmail()).isEqualTo("test@gmail.com");
    }

    @DisplayName("Test user find all")
    @Test
    void listUsers(){
        //given
        User user1 = new User();
        user1.setId(1L);
        user1.setUserName("Test");
        user1.setEmail("test@gmail.com");
        user1.setPassword("12345");
        
        userRepository.save(user);
        userRepository.save(user1);

        //when
        List<User> users = userRepository.findAll();

        //then
        Assertions.assertThat(users.size()).isEqualTo(2);

    }
}
