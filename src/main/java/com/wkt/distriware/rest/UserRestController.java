package com.wkt.distriware.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wkt.distriware.exception.ServiceException;
import com.wkt.distriware.model.User;
import com.wkt.distriware.security.JwtUtils;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/user")
public class UserRestController extends BaseRestController{

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtUtils jwtUtils;
	
	@PostMapping("/auth/signin")
	public RestResult signin(@RequestBody User object) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(object.getUsername(), object.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);
		
		//UserDetails userDetails = (UserDetails) authentication.getPrincipal();		
		
		//List<String> roles = userDetails.getAuthorities().stream()
		//		.map(item -> item.getAuthority())
		//		.collect(Collectors.toList());
		
		RestResult res = new RestResult();
		res.setSuccess(true);
		
		User loginUser;
		try {
			loginUser = controllerUtil.getLoginUser();
			loginUser.setToken(jwt);
			res.setData(loginUser);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			res.setData(jwt);
		}

		return res;
	}
}
