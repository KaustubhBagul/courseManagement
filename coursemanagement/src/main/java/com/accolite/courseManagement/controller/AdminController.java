package com.accolite.courseManagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.accolite.courseManagement.entities.Admin;
import com.accolite.courseManagement.service.AdminService;

@CrossOrigin(origins = "http://localhost:4200")

@RestController
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired 
	private AdminService adminService;
	
	@PostMapping("/save")
	public ResponseEntity<Integer> saveAdmin(@RequestBody Admin admin){
		return new ResponseEntity<>(adminService.saveAdmin(admin), HttpStatus.OK);
	}
	
	@PostMapping("/login")
	public ResponseEntity<Integer> loginAdmin(@RequestBody Admin admin){
		return new ResponseEntity<>(adminService.loginAdmin(admin), HttpStatus.OK);
	}
}
