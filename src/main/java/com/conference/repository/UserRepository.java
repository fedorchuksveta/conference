package com.conference.repository;

import com.conference.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository  extends JpaRepository<User, Long> {
    User deleteUserById(Long id);
    User findAllByFirstName(String name);

}
