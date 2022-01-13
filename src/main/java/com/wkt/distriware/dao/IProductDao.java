package com.wkt.distriware.dao;

import com.wkt.distriware.exception.DaoException;
import com.wkt.distriware.model.Product;

public interface IProductDao {

	Product findWithBarcode(String barcode) throws DaoException;
	
}
