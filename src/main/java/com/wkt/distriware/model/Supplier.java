package com.wkt.distriware.model;

public class Supplier {
	private String supplierCode;
	private String supplierName;
	
	public String getSupplierCode() {
		return supplierCode;
	}
	public void setSupplierCode(String supplierCode) {
		this.supplierCode = supplierCode;
	}
	
	public String getSupplierName() {
		return supplierName;
	}
	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}
	
	public Supplier(String supplierCode, String supplierName) {
		super();
		this.supplierCode = supplierCode;
		this.supplierName = supplierName;
	}
}
