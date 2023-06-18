package com.uservideogames.uservideogames.clients;

import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.uservideogames.uservideogames.models.Videogame;

@FeignClient(name = "videogames", url = "localhost:8002")
public interface VideogameClient {
    //@RequestMapping(value = "/videogames/insert", method = RequestMethod.POST)
    //public ResponseEntity<Map<String,Object>> insertVideogame(@Valid @RequestBody Videogame videogame, BindingResult result);

    //@RequestMapping(value = "/videogames/all", method = RequestMethod.GET)
    //public ResponseEntity<Map<String,Object>> getAllVideogames();

    @RequestMapping(value = "/videogames/find/{id}", method = RequestMethod.POST)
    public Videogame foundVideogameById(@PathVariable Long id);
}
