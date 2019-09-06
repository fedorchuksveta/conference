package com.conference.service;

import com.conference.model.Presentation;
import com.conference.model.User;
import com.conference.repository.PresentationRepository;
import com.conference.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class PresentationService {

    @Autowired
    PresentationRepository presentationRepository;


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

    public Presentation create(Presentation presentation) {
        return presentationRepository.save(presentation);
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
