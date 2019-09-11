package com.conference.service;

import com.conference.dto.PresentationDto;
import com.conference.model.Presentation;
import com.conference.model.Schedule;
import com.conference.model.User;
import com.conference.repository.PresentationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.List;
import java.util.Optional;

@Service
public class PresentationService {

    private final PresentationRepository presentationRepository;
    private final ScheduleService scheduleService;

    public PresentationService(PresentationRepository presentationRepository, ScheduleService scheduleService) {
        this.presentationRepository = presentationRepository;
        this.scheduleService = scheduleService;
    }


    public Presentation getOne(Long id) {
        return presentationRepository.findById(id).orElse(null);
    }

    public List<Presentation> findAll() {
        return presentationRepository.findAll();
    }

    public void delete(Long id) {
        Optional<Presentation> toDelete = presentationRepository.findById(id);
        if (toDelete.isPresent()) {
            presentationRepository.delete(toDelete.get());
        }
    }

    public Presentation save(Presentation presentation) {
        return presentationRepository.save(presentation);
    }

    public boolean validateDateTime(PresentationDto presentationDto) {
        List<Schedule> schedules = scheduleService.findByRoomId(presentationDto.getRoom().getId());
        for (Schedule schedule : schedules) {
            LocalDateTime scheduleDate = schedule.getDate();
            LocalDateTime presentationDate = LocalDateTime.parse(presentationDto.getStart());
            if (Duration.between(scheduleDate, presentationDate).toMinutes() < 30) {
                return false;
            }
        }
        return true;
    }

    @Transactional
    public void savePresentation(PresentationDto presentationDto) {

        Presentation presentation = new Presentation();
        presentation.setName(presentationDto.getName());
        presentation.setRoom(presentationDto.getRoom());
        Presentation newPres = save(presentation);

        Schedule schedule = new Schedule();
        schedule.setDate(LocalDateTime.parse(presentationDto.getStart()));
        schedule.setRoom(presentationDto.getRoom());
        schedule.setPresentation(newPres);
        scheduleService.save(schedule);
    }

//    private Set<Presentation> resolvePresentation(Set<Presentation> presentations) {
//        Set<Presentation> resolved = new HashSet<>(presentations.size());
//        for (Presentation presentation : presentations) {
//            Presentation g = presentationRepository.findByName(presentation.getName());
//            if (g == null) {
//                g = presentationRepository.save(presentation);
//            }
//            resolved.add(g);
//        }
//        return resolved;
//    }
}
