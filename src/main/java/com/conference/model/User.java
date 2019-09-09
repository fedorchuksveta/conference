package com.conference.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue
    private Long id;

    private String firstName;
    private String surName;
    private String userName;
    private String password;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Role role = Role.ADMIN;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "presentation_user",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "presentation_id"))
    private Set<Presentation> presentationSet;

}
