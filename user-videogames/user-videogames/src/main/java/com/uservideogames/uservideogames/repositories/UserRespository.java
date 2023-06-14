package com.uservideogames.uservideogames.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.uservideogames.uservideogames.entity.User;

public interface UserRespository extends JpaRepository<User, Long>{
    public User findByUserName(String userName);
}
