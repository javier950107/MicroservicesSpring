package com.uservideogames.uservideogames.entity;

import java.util.Date;

import org.hibernate.validator.constraints.Range;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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
    
    @Column(name = "game_id")
    @NotNull(message = "Error: the min number for grade must be 1 and the max must be 10")
    private Long gameId;

    @NotEmpty(message = "Error: description doesn't exists")
    private String description;

    @NotEmpty(message = "Error: platform doesn't exists")
    private String platform;

    @Range(min = 1, max = 10, message = "Error: the min number for grade must be 1 and the max must be 10")
    private int grade;

    @Column(name = "end_date")
    @NotNull(message = "Error: endDate doesn't exists")
    private Date endDate;

    @NotNull(message = "Error: User doesn't exists")
    @ManyToOne()
    @JoinColumn(name = "user_id")
    @JsonIgnoreProperties(value={"createAt", "userName", "email", "password"})
    private User user;
}
