package com.wkt.distriware.dao;

import com.wkt.distriware.exception.DaoException;
import com.wkt.distriware.model.User;

public interface IUserDao{

	User findByUserName(String userName) throws DaoException;
	
}
