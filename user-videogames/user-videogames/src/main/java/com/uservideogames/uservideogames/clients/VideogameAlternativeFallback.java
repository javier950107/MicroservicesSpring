package com.uservideogames.uservideogames.clients;

import java.util.List;

import org.springframework.stereotype.Component;

import com.uservideogames.uservideogames.entities.UserVideogames;

@Component
public class VideogameAlternativeFallback {
    
    public List<UserVideogames> alternativeVideogameInfo(Long id, Throwable e){
        System.out.println(e.getMessage());
        
        UserVideogames userVideogames = new UserVideogames();
        userVideogames.setDescription("none");
        userVideogames.setEndDate(null);
        userVideogames.setGameId(0L);
        userVideogames.setGrade(0);
        userVideogames.setPlatform("none");
        userVideogames.setUser(null);
        userVideogames.setVideogame(null);
        List<UserVideogames> response = List.of(userVideogames);
        return response;
    }

    public UserVideogames alternativeInsertVideogame(UserVideogames userVideogames){
        /*userVideogames.setDescription("none");
        userVideogames.setEndDate(null);
        userVideogames.setGameId(0L);
        userVideogames.setGrade(0);
        userVideogames.setPlatform("none");
        userVideogames.setUser(null);
        userVideogames.setVideogame(null);*/
        return null;
    }
}
