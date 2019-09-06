package com.conference.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
@Table(name = "room")
public class Room {

    @Id
    @GeneratedValue
    private Long id;

    private int number;

    @OneToMany(mappedBy="room")
    private Set<Schedule> scheduleSet;

    @OneToMany(mappedBy="room")
    private Set<Presentation> presentationSet;
}
