package com.conference.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
@Table(name = "presentation")
public class Presentation {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @OneToOne(mappedBy = "presentation")
    private Schedule schedule;

    @ManyToMany(mappedBy = "presentationSet")
    private Set<User> userSet;

    @ManyToOne
    @JoinColumn(name="room_id", nullable=false)
    private Room room;
}
