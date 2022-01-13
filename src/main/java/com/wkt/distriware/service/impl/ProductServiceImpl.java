package com.wkt.distriware.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wkt.distriware.dao.IProductDao;
import com.wkt.distriware.exception.BusinessException;
import com.wkt.distriware.exception.DaoException;
import com.wkt.distriware.exception.ServiceException;
import com.wkt.distriware.model.Product;
import com.wkt.distriware.service.api.ProductService;

@Service
public class ProductServiceImpl implements ProductService{

	@Autowired
	private IProductDao productDao;
	
	@Override
	public Product findWithBarcode(String barcode) throws BusinessException, ServiceException {		
		if (barcode == null || barcode.isEmpty()) {
			throw new BusinessException("barcode is required");
		}
		
		Product res = null;
		
		try {
			res = productDao.findWithBarcode(barcode);
		} catch (DaoException e) {
			e.printStackTrace();
			throw new ServiceException("Unable to find product" + e.getMessage());
		}
		
		return res;
	}
	
	
}
