package com.uservideogames.uservideogames.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.uservideogames.uservideogames.entities.UserVideogames;

public interface UserVideogamesRepository extends JpaRepository<UserVideogames, Long>{
    public List<UserVideogames> findByUserId(Long userId); 
}
