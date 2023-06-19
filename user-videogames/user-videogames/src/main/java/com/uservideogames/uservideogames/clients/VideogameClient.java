package com.uservideogames.uservideogames.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.uservideogames.uservideogames.models.Videogame;

@FeignClient(name = "videogames")
public interface VideogameClient {
    //@RequestMapping(value = "/videogames/insert", method = RequestMethod.POST)
    //public ResponseEntity<Map<String,Object>> insertVideogame(@Valid @RequestBody Videogame videogame, BindingResult result);

    //@RequestMapping(value = "/videogames/all", method = RequestMethod.GET)
    //public ResponseEntity<Map<String,Object>> getAllVideogames();

    @RequestMapping(value = "/find/{id}", method = RequestMethod.POST)
    public Videogame foundVideogameById(@PathVariable Long id);
}
