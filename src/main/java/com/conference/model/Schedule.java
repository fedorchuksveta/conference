package com.conference.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity
@Table(name = "schedule")
public class Schedule {

    @Id
    @GeneratedValue
    private Long id;

    @Column(columnDefinition = "DATE")
    private LocalDateTime date;

    @ManyToOne
    @JoinColumn(name = "room_id", nullable = false)
    private Room room;

    @OneToOne
    @JoinColumn(name = "presentation_id", referencedColumnName = "id")
    private Presentation presentation;
}
