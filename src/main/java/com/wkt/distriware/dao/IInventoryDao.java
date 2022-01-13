package com.wkt.distriware.dao;

import java.util.List;

import com.wkt.distriware.exception.DaoException;
import com.wkt.distriware.model.Inventory;
import com.wkt.distriware.model.InventoryItem;
import com.wkt.distriware.model.Product;
import com.wkt.distriware.model.User;

public interface IInventoryDao extends ITransactionDao{

	/**
	 * Find the open inventory schedule
	 * 
	 * @return ID of the inventory schedule
	 * */
	Integer getOpenInventorySchedule();
	
	/**
	 * Get all inventory for the given user
	 * Only list the inventory for the open inventory schedule
	 * 
	 * @param  User
	 * @return List of inventory header
	 * */
	List<Inventory> list(int inventoryScheduleId, User user);
	
	/**
	 * Find inventory by reference  number
	 * 
	 * @param referenceNumber
	 * @return Inventory
	 * */
	Inventory find(int inventoryScheduleId, String referenceNumber) throws DaoException;
	
	/**
	 * Create new blank inventory entry
	 * @return return the reference number of the created inventory entry
	 * */
	String createBlankInventory(int inventoryScheduleId, String referenceNumber, User user) throws DaoException;
	
	
	/**
	 * Find and return blank inventory for the user
	 * 
	 * @return blank inventory else null
	 * */
	Inventory findBlankInventory(User user, int inventoryId);
	
	/**
	 * Update the header part of the inventory entry
	 * 
	 * @return true if affected row is greater that 0 else false
	 * */
	boolean update(int inventoryScheduleId, String reference, String spcode, String stockType) throws DaoException;
	
	/**
	 * 
	 * */
	boolean addInventoryItem(InventoryItem item, Product product) throws DaoException;
	
}
