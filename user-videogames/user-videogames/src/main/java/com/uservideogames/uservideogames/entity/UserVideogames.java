package com.uservideogames.uservideogames.entity;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "videogames")
@Data
@NoArgsConstructor @AllArgsConstructor
public class UserVideogames {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotEmpty(message = "Error: game_id doesn't exists")
    private Long game_id;

    @NotEmpty(message = "Error: description doesn't exists")
    private String description;

    @NotEmpty(message = "Error: platform doesn't exists")
    private String platform;

    @NotEmpty(message = "Error: grade doesn't exists")
    @Size(min = 1, max = 10, message = "Error: the min number for grade must be 1 and the max must be 10")
    private int grade;

    @Column(name = "end_date")
    @NotEmpty(message = "Error: endDate doesn't exists")
    private Date endDate;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}