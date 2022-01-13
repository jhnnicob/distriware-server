package com.wkt.distriware.security;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.wkt.distriware.exception.ServiceException;
import com.wkt.distriware.model.User;
import com.wkt.distriware.service.api.UserService;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

	@Autowired
	private UserService userService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		User appUser = null;
		try {
			appUser = userService.findUserByUserName(username);
		} catch (ServiceException e) {
			e.printStackTrace();
		}

		if (appUser == null) {
			throw new UsernameNotFoundException(username);
		}

		org.springframework.security.core.userdetails.User user = new org.springframework.security.core.userdetails.User(
				appUser.getUsername(), appUser.getPassword(), Collections.emptyList());

		return user;
	}

}
