package com.wkt.distriware.model;

import java.sql.Date;

public class Inventory {

	private int inventoryId;
	private String reference;
	private Date inventoryDate;
	private String supplierCode;
	private String stockType;
	
	public int getInventoryId() {
		return inventoryId;
	}
	public void setInventoryId(int inventoryId) {
		this.inventoryId = inventoryId;
	}
	public String getReference() {
		return reference;
	}
	public void setReference(String reference) {
		this.reference = reference;
	}
	public Date getInventoryDate() {
		return inventoryDate;
	}
	public void setInventoryDate(Date inventoryDate) {
		this.inventoryDate = inventoryDate;
	}
	public String getSupplierCode() {
		return supplierCode;
	}
	public void setSupplierCode(String supplierCode) {
		this.supplierCode = supplierCode;
	}
	public String getStockType() {
		return stockType;
	}
	public void setStockType(String stockType) {
		this.stockType = stockType;
	}
	public Inventory(int inventoryId, String reference, Date inventoryDate, String supplierCode, String stockType) {
		super();
		this.inventoryId = inventoryId;
		this.reference = reference;
		this.inventoryDate = inventoryDate;
		this.supplierCode = supplierCode;
		this.stockType = stockType;
	}
	
	
}
