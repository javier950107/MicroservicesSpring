package com.uservideogames.uservideogames.entities;

import java.sql.Date;

import org.hibernate.annotations.CreationTimestamp;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@Data 
@AllArgsConstructor @NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Error: userName doesn't exists")
    @Column(name = "user_name")
    private String userName;

    @Email(message = "Error: invalid email format")
    @NotEmpty(message = "Error: email doesn't exists")
    private String email;

    @NotEmpty(message = "Error: password doesn't exists")
    private String password;

    @Column(name = "create_at")
    @Temporal(TemporalType.DATE)
    @CreationTimestamp
    private Date createAt;
}
