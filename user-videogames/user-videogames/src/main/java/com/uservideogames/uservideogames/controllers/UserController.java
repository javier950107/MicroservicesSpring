package com.uservideogames.uservideogames.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.uservideogames.uservideogames.entity.User;
import com.uservideogames.uservideogames.services.UserService;
import com.uservideogames.uservideogames.utils.ResponseFormat;

import jakarta.validation.Valid;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private ResponseFormat responseFormat;

    @RequestMapping(value = "/users/register", method = RequestMethod.POST)
    public ResponseEntity<Map<String,Object>> insertUser(@Valid @ModelAttribute User user, BindingResult result){
        try {

            if(result.hasErrors()){
                return ResponseEntity.badRequest().body(responseFormat.handleErrors(result));
            }

            User userInserted = userService.createUser(user);
            if (userInserted == null){
                return ResponseEntity.badRequest().build();
            }else{
                return ResponseEntity.ok(responseFormat.getResponse("Success!", null));
            }
            
        } catch (Exception err) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, err.getMessage());
        }
    }

    @RequestMapping(value = "/users/all", method = RequestMethod.GET)
    public ResponseEntity<Map<String,Object>> getAllUsers(){
        try {
            List<User> users = userService.findAll();
            return ResponseEntity.ok(responseFormat.getResponse("Success", users));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

}
