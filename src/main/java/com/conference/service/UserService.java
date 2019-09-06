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
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PresentationRepository presentationRepository;


    public User getOne(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public void delete(Long id) {
        Optional<User> toDelete = userRepository.findById(id);
        if (toDelete.isPresent()) {
            userRepository.delete(toDelete.get());
        }
    }

    public User create(User user) {
        return userRepository.save(user);
    }

    private Set<Presentation> resolvePresentation(Set<Presentation> presentations) {
        Set<Presentation> resolved = new HashSet<>(presentations.size());
        for (Presentation presentation : presentations) {
            Presentation g = presentationRepository.findByName(presentation.getName());
            if (g == null) {
                g = presentationRepository.save(presentation);
            }
            resolved.add(g);
        }
        return resolved;
    }

}
