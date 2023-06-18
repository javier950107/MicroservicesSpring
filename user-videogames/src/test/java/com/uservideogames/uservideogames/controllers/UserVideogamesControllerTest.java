package com.uservideogames.uservideogames.controllers;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


import java.util.Date;
import java.util.List;

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
import com.uservideogames.uservideogames.entities.User;
import com.uservideogames.uservideogames.entities.UserVideogames;
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
    private UserVideogames userVideogames2;

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
    
        userVideogames2 = new UserVideogames(
                2L, 
                1L, 
                "test", 
                "PS5", 
                6, 
                new Date(System.currentTimeMillis()), 
                null, user);
    }

    @DisplayName("When insert a user videogame")
    @Test
    void whenInsertUserVideogames_ThenReturnUserVideogames() throws JsonProcessingException, Exception{
        //given
        when(userVideogamesService.insertUserVideogames(userVideogames)).thenReturn(userVideogames);

        //when
        ResultActions response = mockMvc.perform(post("/user/videogames/insert")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(userVideogames)));

        //then
        response.andDo(print()).andExpect(status().isOk());
    }

    @DisplayName("Testing when you found all videogames by user")
    @Test
    void findVideogamesByUserIdTest() throws JsonProcessingException, Exception{
        //given
        when(userVideogamesService.getAllVideogamesByUser(1L)).thenReturn(List.of(userVideogames, userVideogames2));

        // when
        ResultActions response = mockMvc.perform(get("/user/videogames/all/1")
            .contentType(MediaType.APPLICATION_JSON));
            //.content(objectMapper.writeValueAsString(userVideogames)));

        //then
        response.andDo(print()).andExpect(status().isOk());
            
    }

    @DisplayName("Testing when the user doesn't exists")
    @Test
    void findVideogameByUserDoenstExists() throws Exception{
        //given
        when(userVideogamesService.getAllVideogamesByUser(3L)).thenReturn(null);

        // when
        ResultActions response = mockMvc.perform(get("/user/videogames/all/3")
            .contentType(MediaType.APPLICATION_JSON));

        //then
        response.andDo(print()).andExpect(status().isBadRequest());
    }

    @DisplayName("Testing when update user videogames")
    @Test
    void testWhenUpdateAUser() throws JsonProcessingException, Exception{
        //given
        UserVideogames userVideogamesUpdate = new UserVideogames(
                1L, 
                1L, 
                "test", 
                "PS5", 
                6, 
                new Date(System.currentTimeMillis()), 
                null, 
                user);
        when(userVideogamesService.updateUserVideogames(userVideogamesUpdate)).thenReturn(userVideogamesUpdate);

        //when
        ResultActions response = mockMvc.perform(post("/user/videogames/update")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(userVideogamesUpdate)));

        //then
        response.andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.data.description").value("test"));
    }

    @DisplayName("Testing when delete an user videogame")
    @Test
    void testWhenDeleteAndVideogameUserController() throws Exception{
        //given
        doNothing().when(userVideogamesService).deleteById(userVideogames);

        //when
        ResultActions response = mockMvc.perform(post("/user/videogames/delete")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(userVideogames)));

        //then
        response.andDo(print())
            .andExpect(status().isOk());
    }

}
