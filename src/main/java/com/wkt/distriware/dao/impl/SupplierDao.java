package com.wkt.distriware.dao.impl;

import static com.wkt.distriware.util.StringUtil.trim;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.wkt.distriware.dao.ISupplierDao;
import com.wkt.distriware.exception.DaoException;
import com.wkt.distriware.model.Supplier;

@Repository
public class SupplierDao extends BaseDao implements ISupplierDao {

	@Override
	public List<Supplier> getSupplierList(String queryString) throws DaoException {

		String query = "Select Top 10 SPNAME, SPCODE FROM "
				+ "SUPPLIER WHERE SPCODE Like '%" + queryString + "' Or SPNAME Like '%" + queryString + "'";
		
		return jdbcTemplate.query(query, (rs, rowNum) -> 
			new Supplier(
					trim(rs.getString("SPCODE")), 
					trim(rs.getString("SPNAME"))
				)
		);
	}
}
