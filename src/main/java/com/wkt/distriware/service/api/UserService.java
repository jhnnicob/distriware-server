package com.wkt.distriware.service.api;

import com.wkt.distriware.exception.ServiceException;
import com.wkt.distriware.model.User;

public interface UserService{

	User findUserByUserName(String userName) throws ServiceException;
	
}
