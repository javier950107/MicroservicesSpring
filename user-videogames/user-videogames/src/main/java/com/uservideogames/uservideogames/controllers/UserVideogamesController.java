package com.uservideogames.uservideogames.controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.uservideogames.uservideogames.entities.UserVideogames;
import com.uservideogames.uservideogames.services.UserVideogamesService;
import com.uservideogames.uservideogames.utils.ResponseFormat;

import jakarta.validation.Valid;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
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
    public ResponseEntity<Map<String,Object>> insertUserVideogames(@Valid @RequestBody UserVideogames userVideogames, BindingResult result) {
        try {
            if(result.hasErrors()){
                return ResponseEntity.badRequest().body(responseFormat.handleErrors(result));
            }

            UserVideogames userInserted = userVideogamesService.insertUserVideogames(userVideogames);
            if (userInserted != null){
                return ResponseEntity.ok().body(responseFormat.getResponse("Success", null));
            }else{
                return ResponseEntity.badRequest().body(responseFormat.getResponse("Error: User or videogame doesn't exists", null));
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @RequestMapping(value = "/videogames/all/{id}" , method = RequestMethod.GET)
    public ResponseEntity<Map<String,Object>> getVideogamesByUser(@PathVariable("id") Long id){
        try {
            List<UserVideogames> videogamesFound = userVideogamesService.getAllVideogamesByUser(id);
            if(videogamesFound != null){
                return ResponseEntity.ok(responseFormat.getResponse("Success", videogamesFound));
            }else{
                return ResponseEntity.badRequest().body(responseFormat.getResponse("Error: User doesn't exists", null));
            }
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @RequestMapping(value = "/videogames/delete", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteUserVideogame(@RequestBody UserVideogames userVideogames){
        try {
            userVideogamesService.deleteById(userVideogames);
            return ResponseEntity.ok(responseFormat.getResponse("Success!", null));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @RequestMapping(value = "/videogames/update", method = RequestMethod.POST)
    public ResponseEntity<?> updateUserVideogames(@RequestBody UserVideogames userVideogames ,@Valid BindingResult result ){
        try {

            if(result.hasErrors()){
                return ResponseEntity.badRequest().body(responseFormat.handleErrors(result));
            }

            UserVideogames updatedUser = userVideogamesService.updateUserVideogames(userVideogames);
            if(updatedUser != null){
                return ResponseEntity.ok(responseFormat.getResponse("Success!", updatedUser));
            }else{
                return ResponseEntity.badRequest().body(responseFormat.getResponse("Error: Videogame doesn't exists", null));
            }
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
    
}
