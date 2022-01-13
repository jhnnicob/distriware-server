package com.wkt.distriware.service.api;

import java.util.List;

import com.wkt.distriware.exception.BusinessException;
import com.wkt.distriware.exception.DaoException;
import com.wkt.distriware.exception.ServiceException;
import com.wkt.distriware.model.Inventory;
import com.wkt.distriware.model.InventoryItem;
import com.wkt.distriware.model.Supplier;

public interface SupplierService {

	List<Supplier> search(String keyword) throws BusinessException, ServiceException;
 
}
