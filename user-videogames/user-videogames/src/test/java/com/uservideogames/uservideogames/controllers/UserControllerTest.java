package com.uservideogames.uservideogames.controllers;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.uservideogames.uservideogames.entities.User;
import com.uservideogames.uservideogames.services.UserService;

@AutoConfigureMockMvc
@SpringBootTest
public class UserControllerTest {
    
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Autowired
    private ObjectMapper objectMapper;

    private User user;

    @BeforeEach
    void setUp(){
        user = new User();
        user.setId(1L);
        user.setUserName("Test");
        user.setEmail("test@gmail.com");
        user.setPassword("12345");
    }


    @DisplayName("Testing on save user rest")
    @Test
    void saveUserTest() throws Exception{
        //given
        when(userService.createUser(user)).thenReturn(user);  

        //when
        ResultActions response = mockMvc.perform(post("/register")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(user)));

        //then
        response.andDo(print())
            .andExpect(status().isOk());
    }

    @DisplayName("Testing when get all users")
    @Test
    void findUserTest() throws JsonProcessingException, Exception{
        //given
        when(userService.findAllUsers()).thenReturn(List.of(user));

        //when
        ResultActions response = mockMvc.perform(get("/all")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(user)));

        //then

        response.andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.msg").value("Success"))
            .andExpect(jsonPath("$.data[0].userName").value("Test"));
    }

    @DisplayName("Testing login accept")
    @Test
    void onAuthUserTest_ThenReturnAccess() throws JsonProcessingException, Exception{
        //given
        when(userService.onAuthUser(user.getUserName(), user.getPassword())).thenReturn(user);

        //when
        ResultActions response = mockMvc.perform(post("/login")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(user)));

        //then
        response.andDo(print())
            .andExpect(status().isOk());
    }

    @DisplayName("Testing login unauthorized")
    @Test
    void onAuthUserTest_ThenReturnInvalid() throws JsonProcessingException, Exception{
        //given
        User userInvalid = new User();
        userInvalid.setUserName("test");
        userInvalid.setPassword("3");

        when(userService.onAuthUser(userInvalid.getUserName(), userInvalid.getPassword())).thenReturn(null);

        //when
        ResultActions response = mockMvc.perform(post("/login")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(userInvalid)));

        //then
        response.andDo(print())
            .andExpect(status().is(403));
    }

}
