package com.wkt.distriware.service.api;

import java.util.List;

import com.wkt.distriware.exception.BusinessException;
import com.wkt.distriware.exception.DaoException;
import com.wkt.distriware.exception.ServiceException;
import com.wkt.distriware.model.Inventory;
import com.wkt.distriware.model.InventoryItem;

public interface InventoryService {

	/**
	 * Get all inventory list created by the user
	 * Check if user role can view other inventory item
	 * 
	 * @throws ServiceException 
	 * */
	List<Inventory> listInventoryByUser() throws BusinessException, ServiceException;
	
	/**
	 * Save and return new inventory entry
	 * Return previously created blank inventory if present
	 * 
	 * @throws ServiceException 
	 * @throws DaoException 
	 * */
	Inventory createNewInventory() throws BusinessException, ServiceException;
	
	/**
	 * Get inventory header only
	 * 
	 * @param reference - Reference of the inventory entry to find
	 * @return Inventory entry header
	 * @throws DaoException 
	 * */
	Inventory getInventoryHeader(String reference) throws BusinessException, ServiceException;
	
	/**
	 * Update inventory header
	 * 
	 * @param spcode - Supplier code
	 * @param stockType - Item stock type (Good,Bad)
	 * @return Updated inventory header
	 * */
	Inventory updateInventoryHeader(String reference, String spcode, String stockType) throws BusinessException, ServiceException;
	
	/**
	 * 
	 */
	 InventoryItem addInventoryItem(InventoryItem item) throws BusinessException, ServiceException;
	 
}
