package com.accolite.courseManagement.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.accolite.courseManagement.entities.Creator;

@Repository
public interface CreatorRepository extends JpaRepository<Creator, Long> {

	Creator findByName(String name);
}
