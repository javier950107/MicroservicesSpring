package com.uservideogames.uservideogames.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uservideogames.uservideogames.clients.VideogameClient;
import com.uservideogames.uservideogames.entities.User;
import com.uservideogames.uservideogames.entities.UserVideogames;
import com.uservideogames.uservideogames.models.Videogame;
import com.uservideogames.uservideogames.repositories.UserRepository;
import com.uservideogames.uservideogames.repositories.UserVideogamesRepository;


@Service
public class UserVideogamesServiceImp implements UserVideogamesService{

    @Autowired
    private UserVideogamesRepository userVideogameRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private VideogameClient videogameClient;

    @Override
    public UserVideogames insertUserVideogames(UserVideogames userVideogames) {
        Optional<User> userFound = userRepository.findById(userVideogames.getUser().getId());
        Videogame videogameFound = videogameClient.foundVideogameById(userVideogames.getGameId());
        
        if(userFound.isPresent() && videogameFound != null){
            if(videogameFound.getId().equals(userVideogames.getGameId())){
                return userVideogameRepository.save(userVideogames);
            }
            return null;      
        }
        return null;
    }

    @Override
    public List<UserVideogames> getAllVideogamesByUser(Long id) {
        Optional<User> userFound = userRepository.findById(id);//.orElse(n);
        if (userFound.isPresent()){
            List<UserVideogames> userVideogames = userVideogameRepository.findByUserId(id)
                .stream()
                .map(info ->{
                    Videogame videogameFound = videogameClient.foundVideogameById(info.getGameId());
                    info.setVideogame(videogameFound);
                    return info; 
                })
                .collect(Collectors.toList());
            return userVideogames;
        }else{
            return null;
        }

    }

    @Override
    public void deleteById(UserVideogames userVideogames) {
        userVideogameRepository.deleteById(userVideogames.getId());
    }

    @Override
    public UserVideogames updateUserVideogames(UserVideogames userVideogames) {
        Optional<UserVideogames> userFound = userVideogameRepository.findById(userVideogames.getId());
        if(userFound.isPresent()){
            return userVideogameRepository.save(userVideogames);
        }else{
            return null;
        }
    }

    
    
}
