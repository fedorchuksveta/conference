package com.conference.repository;

import com.conference.model.Presentation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PresentationRepository  extends JpaRepository<Presentation, Long> {
}
