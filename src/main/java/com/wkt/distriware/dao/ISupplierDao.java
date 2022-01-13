package com.wkt.distriware.dao;

import java.util.List;

import com.wkt.distriware.exception.DaoException;
import com.wkt.distriware.model.Supplier;

public interface ISupplierDao {

	List<Supplier> getSupplierList(String keword) throws DaoException;
	
}
