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
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RoleId", nullable = false)
    private Integer id;

    @Column(name = "RoleTitle", length = 50)
    private String roleTitle;

    @OneToMany(mappedBy = "role")
    private Set<Userinrole> userinroles = new LinkedHashSet<>();

}