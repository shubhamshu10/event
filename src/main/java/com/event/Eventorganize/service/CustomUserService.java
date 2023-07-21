package com.event.Eventorganize.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.event.Eventorganize.entity.CustomUser;
import com.event.Eventorganize.entity.User;
import com.event.Eventorganize.repository.UserRepository;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class CustomUserService implements UserDetailsService{

	@Autowired
	private UserRepository repo;
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Optional<User> user=repo.findUserByEmail(email);
		user.orElseThrow(()-> new UsernameNotFoundException("Invalid username"));
		return user.map(CustomUser::new).get();
	}
}
