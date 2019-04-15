package com.myhome.springCrudRest.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserService userService;

//	@Transactional(readOnly=true) //todo зачем?
	@Override
	public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {

		UserDetails userDetails = userService.getByUserName(username).orElseThrow(IllegalArgumentException::new);
		System.out.println("login:" + userDetails.getUsername() + "; password:" + userDetails.getPassword());

		return userDetails;
	}
}