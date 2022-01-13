package com.wkt.distriware.dao.impl;

import static com.wkt.distriware.util.StringUtil.trim;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.wkt.distriware.dao.IUserDao;
import com.wkt.distriware.exception.DaoException;
import com.wkt.distriware.model.User;
import com.wkt.distriware.util.ListUtil;

@Repository
public class UserDao extends BaseDao implements IUserDao {

	@Override
	public User findByUserName(String userName) throws DaoException {
		User res = null;
		String query = "Select * from [user] where username = '"+ userName +"'";
		List<User> list = jdbcTemplate.query(query,  (rs, rowNum) ->
        new User(
                trim(rs.getString("userid")),
                trim(rs.getString("username")),
                trim(rs.getString("password")),
                trim(rs.getString("usertype"))
        )); 
		
		if (ListUtil.isNullOrEmpty(list)) {
		}else {
			res = list.get(0);
		}
		
		return res;
	}
	
}
