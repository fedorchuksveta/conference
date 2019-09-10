package com.conference.service;

import com.conference.dto.PresentationDto;
import com.conference.model.Presentation;
import com.conference.model.Schedule;
import com.conference.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class ScheduleService {
    @Autowired
    private ScheduleRepository scheduleRepository;

    @Autowired
    private PresentationService presentationService;

    @Autowired
    private ScheduleService scheduleService;


    public Schedule save(Schedule schedule) {
        return scheduleRepository.save(schedule);
    }

    @Transactional
    public String savePresentation(PresentationDto presentationDto) {

        Presentation presentation = new Presentation();
        presentation.setName(presentationDto.getName());
        presentation.setRoom(presentationDto.getRoom());
        Presentation newPres = presentationService.save(presentation);

        Schedule schedule = new Schedule();
        schedule.setDate(LocalDateTime.parse(presentationDto.getStart()));
        schedule.setRoom(presentationDto.getRoom());
        schedule.setPresentation(newPres);
        scheduleService.save(schedule);
        return "presentationSuccess";
    }

}
