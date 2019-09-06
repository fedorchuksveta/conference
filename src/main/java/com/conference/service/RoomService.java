package com.conference.service;

import com.conference.model.Room;
import com.conference.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoomService {

    @Autowired
    RoomRepository roomRepository;

    public Room create(Room room) {
        return roomRepository.save(room);
    }
}
