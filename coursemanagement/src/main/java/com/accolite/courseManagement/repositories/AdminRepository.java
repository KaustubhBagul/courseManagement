package com.accolite.courseManagement.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.accolite.courseManagement.entities.Admin;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long>  {

	Admin findByEmail(String email);
	Admin findByUsername(String username);
}
