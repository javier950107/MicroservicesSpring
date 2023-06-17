package com.uservideogames.uservideogames.controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.uservideogames.uservideogames.entity.UserVideogames;
import com.uservideogames.uservideogames.services.UserVideogamesService;
import com.uservideogames.uservideogames.utils.ResponseFormat;

import jakarta.validation.Valid;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@RestController
public class UserVideogamesController {

    @Autowired
    private UserVideogamesService userVideogamesService;

    @Autowired
    private ResponseFormat responseFormat;
    
    @RequestMapping(value="/videogames/insert", method=RequestMethod.POST)
    public ResponseEntity<Map<String,Object>> requestMethodName(@Valid @RequestBody UserVideogames userVideogames, BindingResult result) {
        try {

            if(result.hasErrors()){
                return ResponseEntity.badRequest().body(responseFormat.handleErrors(result));
            }

            UserVideogames userInserted = userVideogamesService.insertUserVideogames(userVideogames);
            if (userInserted != null){
                return ResponseEntity.ok().body(responseFormat.getResponse("Success", null));
            }else{
                return ResponseEntity.badRequest().body(responseFormat.getResponse("Error: User doesn't exists", null));
            }

        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
    
}
