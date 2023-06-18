package com.videogames.videogames.controllers;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.videogames.videogames.entities.Videogame;
import com.videogames.videogames.services.VideogameService;
import com.videogames.videogames.utils.ResponseFormat;

import jakarta.validation.Valid;

@RestController
public class VideogameController {

    @Autowired
    private VideogameService videogameService;

    @Autowired
    private ResponseFormat responseFormat;

    @RequestMapping(value = "/videogames/insert", method = RequestMethod.POST)
    public ResponseEntity<Map<String,Object>> insertVideogame(@Valid @RequestBody Videogame videogame, BindingResult result){
        try {
            if(result.hasErrors()){
                return ResponseEntity.badRequest().body(responseFormat.handleErrors(result));
            }

            Videogame inserted = videogameService.insertVideogame(videogame);
            if (inserted != null){
                return ResponseEntity.ok(responseFormat.getResponse("Success: Videogame inserted", inserted));
            }else{
                return ResponseEntity.badRequest().body(responseFormat.getResponse("Error: Videogame exists", null));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
        }
    }

    @RequestMapping(value = "/videogames/all", method = RequestMethod.GET)
    public ResponseEntity<Map<String,Object>> getAllVideogames(){
        try {
            List<Videogame> videogames = videogameService.getAllVideogame();
            return ResponseEntity.ok(responseFormat.getResponse("Success", videogames));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @RequestMapping(value = "/videogames/get/{id}", method = RequestMethod.POST)
    public ResponseEntity<Map<String,Object>> getVideogameById(@PathVariable Long id){
        try {
            Videogame found = videogameService.getVideogameById(id);
            if (found!= null){
                return ResponseEntity.ok(responseFormat.getResponse("Success!", found));
            }else{
                return ResponseEntity.badRequest().body(responseFormat.getResponse("Error: Videogame doesn't exists", null));
            }
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @RequestMapping(value = "/videogames/find/{id}", method = RequestMethod.POST)
    public Videogame foundVideogameById(@PathVariable Long id){
        try {
            Videogame found = videogameService.getVideogameById(id);
            if (found!= null){
                return found;
            }else{
                return null;
            }
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}
