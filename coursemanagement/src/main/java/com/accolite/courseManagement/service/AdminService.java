package com.accolite.courseManagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accolite.courseManagement.entities.Admin;
import com.accolite.courseManagement.repositories.AdminRepository;

@Service
public class AdminService {

	@Autowired
	private AdminRepository adminRepository;
	
	public Admin getAdminByEmail(String email) {

		Admin admin = adminRepository.findByEmail(email);
		return admin;
	}
	
	public Admin getAdminByUsername(String username) {

		Admin admin = adminRepository.findByUsername(username);
		return admin;
	}
	
	public int saveAdmin(Admin admin){
		if(this.getAdminByEmail(admin.getEmail()) != null)
			return 1;
		if(this.getAdminByUsername(admin.getUsername()) != null)
			return 2;
		this.adminRepository.save(admin);
		return 0;
	}
	public int loginAdmin(Admin admin) {
		Admin respAdmin = adminRepository.findByUsername(admin.getUsername());
		if(respAdmin == null || !(respAdmin.getPassword().equals(admin.getPassword()))) {
			System.out.println("entered");
			return -1;
		}
		return 1;
	}
}
