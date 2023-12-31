package com.uservideogames.uservideogames;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class UserVideogamesApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserVideogamesApplication.class, args);
	}

}
