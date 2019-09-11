package com.conference.service;

import com.conference.model.Role;
import com.conference.model.User;
import com.conference.repository.PresentationRepository;
import com.conference.repository.RoleRepository;
import com.conference.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


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

    public void addListener(Long id) {
        Optional<User> addListener = userRepository.findById(id);
        if (addListener.isPresent()) {
            presentationRepository.getOne(id);
        }
    }

    public User create(User user) {
        Set<Role> roles = new HashSet<>();
        roles.add(roleRepository.findByName("listener"));
        user.setRoles(roles);
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
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
                Set<Role> roles = new HashSet<>();
                roles.add(roleRepository.findByName("listener"));
                newUser.setRoles(roles);
                newUser.setUserName(user.getUserName());

                newUser = userRepository.save(newUser);

                return newUser;
            } else {
                user = userRepository.save(user);

                return user;
            }
        }
    }

    public Optional<User> findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

}
