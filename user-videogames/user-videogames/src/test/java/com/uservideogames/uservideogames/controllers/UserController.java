package com.uservideogames.uservideogames.controllers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.uservideogames.uservideogames.entity.User;
import com.uservideogames.uservideogames.services.UserService;

@WebMvcTest
public class UserController {
    
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void saveUserTest() throws JsonProcessingException, Exception{
        //given
        User user = new User();
        user.setId(1L);
        user.setUserName("Test");
        user.setEmail("test@gmail.com");
        user.setPassword("12345");

        when(userService.createUser(any(User.class))).thenReturn(user);
        
        //when
        ResultActions response = mockMvc.perform(post("/users/register")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(user)));

        //then
        response.andDo(print())
            .andExpect(status().isCreated());
    }
}
