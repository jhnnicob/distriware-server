package com.wkt.distriware.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wkt.distriware.dao.IProductDao;
import com.wkt.distriware.dao.ISupplierDao;
import com.wkt.distriware.exception.BusinessException;
import com.wkt.distriware.exception.DaoException;
import com.wkt.distriware.exception.ServiceException;
import com.wkt.distriware.model.Product;
import com.wkt.distriware.model.Supplier;
import com.wkt.distriware.service.api.ProductService;
import com.wkt.distriware.service.api.SupplierService;

@Service
public class SupplierServiceImpl implements SupplierService{

	@Autowired
	private ISupplierDao supplierDao;

	@Override
	public List<Supplier> search(String keyword) throws BusinessException, ServiceException {
		List<Supplier> res = null;
		
		try {
			res = supplierDao.getSupplierList(keyword);
		} catch (DaoException e) {
			e.printStackTrace();
			throw new ServiceException("Unable to find Supplier" + e.getMessage());
		}
		
		return res;
	}
}
