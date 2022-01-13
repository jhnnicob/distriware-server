package com.wkt.distriware.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.wkt.distriware.exception.ServiceException;
import com.wkt.distriware.model.User;
import com.wkt.distriware.service.api.UserService;

@Component
public class ControllerUtil {

	@Autowired
	private UserService userService;
	
	public User getLoginUser() throws ServiceException {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		
		return userService.findUserByUserName(userDetails.getUsername());
		
	}
	
}
