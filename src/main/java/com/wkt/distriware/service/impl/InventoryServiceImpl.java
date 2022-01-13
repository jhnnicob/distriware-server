package com.wkt.distriware.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wkt.distriware.dao.IInventoryDao;
import com.wkt.distriware.exception.BusinessException;
import com.wkt.distriware.exception.DaoException;
import com.wkt.distriware.exception.ServiceException;
import com.wkt.distriware.model.Inventory;
import com.wkt.distriware.model.InventoryItem;
import com.wkt.distriware.model.User;
import com.wkt.distriware.rest.ControllerUtil;
import com.wkt.distriware.service.api.InventoryService;

@Service
public class InventoryServiceImpl implements InventoryService {

	@Autowired
	private IInventoryDao inventoryDao;

	@Autowired
	ControllerUtil controlUtil;

	@Override
	public List<Inventory> listInventoryByUser() throws BusinessException, ServiceException {
		User user = controlUtil.getLoginUser();

		Integer inventoryId = inventoryDao.getOpenInventorySchedule();

		if (inventoryId == null || inventoryId == 0) {
			throw new BusinessException("No active inventory schedule");
		}

		return inventoryDao.list(inventoryId, user);
	}

	@Override
	public Inventory getInventoryHeader(String reference) throws BusinessException, ServiceException {
		
		Integer inventoryId = inventoryDao.getOpenInventorySchedule();
		if (inventoryId == null || inventoryId == 0) {
			throw new BusinessException("No active inventory schedule");
		}
		
		try {
			return inventoryDao.find(inventoryId, reference);
		} catch (DaoException e) {
			e.printStackTrace();
			throw new ServiceException("Unable to find inventory. " + e.getMessage(), e);
		}
	}

	@Override
	public Inventory createNewInventory() throws BusinessException, ServiceException {
		// Check if there is an open inventory
		Integer inventoryId = inventoryDao.getOpenInventorySchedule();

		if (inventoryId == null || inventoryId == 0) {
			throw new BusinessException("No active inventory schedule");
		}

		// check if there is a blank inventory
		Inventory blankInventory = inventoryDao.findBlankInventory(controlUtil.getLoginUser(), inventoryId);
		if (blankInventory != null) {
			return blankInventory;
		}

		// Get new reference number
		String referenceNumber = inventoryDao.getReferenceNumber("INVENTORY", "INVOICE", inventoryId);
		if (referenceNumber == null) {
			throw new BusinessException("Unable to get inventory reference number");
		}

		// Create blank inventory
		String finalReference = null;
		try {
			finalReference = inventoryDao.createBlankInventory(inventoryId, referenceNumber,
					controlUtil.getLoginUser());
		} catch (DaoException e) {
			e.printStackTrace();
			throw new ServiceException("Unable to create blank inventory. " + e.getMessage(), e);
		}

		if (finalReference == null) {
			throw new ServiceException("Unable to create inventory entry. Reference is null");
		}

		Inventory res = getInventoryHeader(finalReference);

		if (res == null) {
			throw new ServiceException(
					"Unable to create inventory entry. Reference number is created but record is not found");
		}

		return res;
	}

	@Override
	public Inventory updateInventoryHeader(String reference, String spcode, String stockType)
			throws BusinessException, ServiceException {
		Inventory res = null;
		
		Integer inventoryId = inventoryDao.getOpenInventorySchedule();

		if (inventoryId == null || inventoryId == 0) {
			throw new BusinessException("No active inventory schedule");
		}
		
		Inventory current = getInventoryHeader(reference);
		if (current == null) {
			throw new BusinessException("Unable to find inventory reference number " + reference);
		}
		
		try {
			//try to update inventory headers
			boolean affected = inventoryDao.update(inventoryId, reference, spcode, stockType);
			if (affected) {
				//get the updated inventory header
				res = getInventoryHeader(reference);
			}else {
				throw new ServiceException("Unable to update inventory. No record is affected by the update query");
			}
			
		} catch (DaoException e) {
			e.printStackTrace();
			throw new ServiceException("Unable to update inventory. " + e.getMessage(), e);
		}
		
		return res;
	}
	
	@Override
	public InventoryItem addInventoryItem(InventoryItem item)
			throws BusinessException, ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

}
