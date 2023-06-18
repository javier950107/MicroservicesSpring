package com.videogames.videogames.entity;

import jakarta.validation.constraints.NotEmpty;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "videogames")
@Data
@NoArgsConstructor @AllArgsConstructor
public class Videogame {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Error: videogame doesn't exists")
    @Column(name = "name_videogame")
    private String videogameName;
}
