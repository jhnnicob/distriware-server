package com.wkt.distriware.model;

public class Product {

	private String code;
	private String description;
	private String unitCase;
	private String unitPcs;
	private String barcodeCase;
	private String barcodePcs;
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getBarcodeCase() {
		return barcodeCase;
	}
	public void setBarcodeCase(String barcodeCase) {
		this.barcodeCase = barcodeCase;
	}
	public String getBarcodePcs() {
		return barcodePcs;
	}
	public void setBarcodePcs(String barcodePcs) {
		this.barcodePcs = barcodePcs;
	}
	public String getUnitCase() {
		return unitCase;
	}
	public void setUnitCase(String unitCase) {
		this.unitCase = unitCase;
	}
	public String getUnitPcs() {
		return unitPcs;
	}
	public void setUnitPcs(String unitPcs) {
		this.unitPcs = unitPcs;
	}
	
	public Product(String code, String description, String unitCase, String unitPcs, String barcodeCase,
			String barcodePcs) {
		super();
		this.code = code;
		this.description = description;
		this.unitCase = unitCase;
		this.unitPcs = unitPcs;
		this.barcodeCase = barcodeCase;
		this.barcodePcs = barcodePcs;
	}
	
	
}
