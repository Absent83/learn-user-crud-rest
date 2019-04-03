package com.myhome.springCrudRest.service;


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

@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserService userService;

	//@Transactional(readOnly=true) //todo зачем?
	@Override
	public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
	
		Optional<com.myhome.springCrudRest.model.User> userCandidate = userService.getByLogin(username);

		com.myhome.springCrudRest.model.User user = userCandidate.get();

		List<GrantedAuthority> authorities = buildUserAuthority(Collections.singleton(user.getUserRole())); //todo переделать на Set

		return buildUserForAuthentication(user, authorities);
		
	}

	// Converts com.mkyong.users.model.User user to
	// org.springframework.security.core.userdetails.User
	private User buildUserForAuthentication(com.myhome.springCrudRest.model.User user, List<GrantedAuthority> authorities) {
		return new User(user.getLogin(), user.getPassword(), true, true, true, true, authorities);
	}

	private List<GrantedAuthority> buildUserAuthority(Set<UserRole> userRoles) {

		Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();

		// Build user's authorities
		for (UserRole userRole : userRoles) {
			setAuths.add(new SimpleGrantedAuthority(userRole.toString()));
		}

		List<GrantedAuthority> Result = new ArrayList<GrantedAuthority>(setAuths);

		return Result;
	}

}