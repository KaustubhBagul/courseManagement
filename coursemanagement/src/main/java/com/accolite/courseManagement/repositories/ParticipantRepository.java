package com.accolite.courseManagement.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.accolite.courseManagement.entities.Participants;

@Repository
public interface ParticipantRepository extends JpaRepository<Participants, Long> {

	Participants findByEmail(String email);
	Participants findByUsername(String username);
}
