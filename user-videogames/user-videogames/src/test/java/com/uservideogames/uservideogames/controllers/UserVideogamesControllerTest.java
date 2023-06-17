package com.uservideogames.uservideogames.controllers;

import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


import java.util.Date;

import org.springframework.http.MediaType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.uservideogames.uservideogames.entity.User;
import com.uservideogames.uservideogames.entity.UserVideogames;
import com.uservideogames.uservideogames.services.UserVideogamesService;

@AutoConfigureMockMvc
@SpringBootTest
public class UserVideogamesControllerTest {
    
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserVideogamesService userVideogamesService;

    @Autowired
    private ObjectMapper objectMapper;

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
        userVideogames.setId(1L);
        userVideogames.setPlatform("Switch");
        userVideogames.setUser(user);
    }

    @DisplayName("When insert a user videogame")
    @Test
    void whenInsertUserVideogames_ThenReturnUserVideogames() throws JsonProcessingException, Exception{
        //given
        when(userVideogamesService.insertUserVideogames(userVideogames)).thenReturn(userVideogames);

        //when
        ResultActions response = mockMvc.perform(post("/videogames/insert")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(userVideogames)));

        //then
        response.andDo(print()).andExpect(status().isOk());
            
    }

}
