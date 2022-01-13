package com.wkt.distriware.service.api;

import com.wkt.distriware.exception.BusinessException;
import com.wkt.distriware.exception.ServiceException;
import com.wkt.distriware.model.Product;

public interface ProductService {

	Product findWithBarcode(String barcode) throws BusinessException, ServiceException;
	
}
