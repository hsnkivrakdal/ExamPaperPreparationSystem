package com.example.eppms.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "UserId", nullable = false)
    private Integer id;

    @Column(name = "UserFirstName", nullable = false, length = 50)
    private String userFirstName;

    @Column(name = "UserLastName", nullable = false, length = 50)
    private String userLastName;

    @Column(name = "UserEmail", nullable = false, length = 50)
    private String userEmail;

    @Column(name = "UserPassword", nullable = false, length = 20)
    private String userPassword;

    @OneToMany(mappedBy = "user")
    private Set<Lecturer> lecturers = new LinkedHashSet<>();

    @OneToMany(mappedBy = "user")
    private Set<Userinrole> userinroles = new LinkedHashSet<>();

}