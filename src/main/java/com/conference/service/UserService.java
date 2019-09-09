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


    public User createOrUpdateUser(User user) {
        if(user.getId()  == null) {
            user = userRepository.save(user);
            return user;
        } else {
            Optional<User> userOp = userRepository.findById(user.getId());
            if(userOp.isPresent()) {
                User newUser = userOp.get();
                newUser.setFirstName(user.getFirstName());
                newUser.setSurName(user.getSurName());
                newUser.setPassword(user.getPassword());
                newUser.setRole(user.getRole());
                newUser.setUserName(user.getUserName());

                newUser = userRepository.save(newUser);

                return newUser;
            } else {
                user = userRepository.save(user);

                return user;
            }
        }
    }
}
