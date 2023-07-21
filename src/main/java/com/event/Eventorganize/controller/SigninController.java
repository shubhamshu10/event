package com.event.Eventorganize.controller;

import java.util.ArrayList;
import java.util.List;

//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.event.Eventorganize.entity.*;
import com.event.Eventorganize.repository.RoleRepository;
import com.event.Eventorganize.repository.UserRepository;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;


@Controller
public class SigninController {
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private RoleRepository roleRepo;
	@GetMapping("/login")
    public String login() {
		
    	return "login";
    }
	@GetMapping("/register")
    public String register() {
    	return "register";
    }
	@GetMapping("/logout")
    public String logout() {
    	return "logout";
    }
	@PostMapping("/register")
	public String registerUser(@ModelAttribute("user") User user,HttpServletRequest request) throws ServletException {
		String password = user.getPassword();
		user.setPassword(passwordEncoder.encode(password));
		List<Role> roles = new ArrayList<>();
		roles.add(roleRepo.findById(2).get());
		user.setRoles(roles);
		userRepo.save(user);
		request.login(user.getEmail(), user.getPassword());
		return "redirect:/home";
	}
}
