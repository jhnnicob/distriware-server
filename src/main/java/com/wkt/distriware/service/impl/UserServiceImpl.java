package com.wkt.distriware.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wkt.distriware.dao.IUserDao;
import com.wkt.distriware.exception.ServiceException;
import com.wkt.distriware.model.User;
import com.wkt.distriware.service.api.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	IUserDao userDao;

	
	@Override
	public User findUserByUserName(String userName) throws ServiceException {
		User res = null;
		try {
			res = userDao.findByUserName(userName) ;
		} catch (Exception e) {
			throw new ServiceException(e);
		}
		
		return res;
		
	}
}
