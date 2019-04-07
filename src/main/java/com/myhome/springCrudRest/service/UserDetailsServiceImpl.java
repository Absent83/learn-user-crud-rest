package com.myhome.springCrudRest.service;


import com.myhome.springCrudRest.model.UserDetailsImpl;
import com.myhome.springCrudRest.model.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserService userService;

	//@Transactional(readOnly=true) //todo зачем?
	@Override
	public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {

		UserDetails userDetails = new UserDetailsImpl(userService.getByLogin(username).orElseThrow(IllegalArgumentException::new));
		System.out.println("login:" + userDetails.getUsername() + "; password:" + userDetails.getPassword());

		for (GrantedAuthority authority : userDetails.getAuthorities()) {
			System.out.println(authority.getAuthority());
		}


		return userDetails;
	}
}