package com.conference.dto;

import com.conference.model.Room;
import lombok.Data;

@Data
public class PresentationDto {

    private String name;
    private String start;
    private String finish;
    private Room room;
}
