package com.wkt.distriware.model;

public class InventoryItem {

	private String reference;
	private int num;
	private String productCode;
	private String qtyCase;
	private String qttPcs;
	private String unitUsed;
	private Product product;
	
	
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public String getReference() {
		return reference;
	}
	public void setReference(String reference) {
		this.reference = reference;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	public String getQtyCase() {
		return qtyCase;
	}
	public void setQtyCase(String qtyCase) {
		this.qtyCase = qtyCase;
	}
	public String getQttPcs() {
		return qttPcs;
	}
	public void setQttPcs(String qttPcs) {
		this.qttPcs = qttPcs;
	}
	public String getUnitUsed() {
		return unitUsed;
	}
	public void setUnitUsed(String unitUsed) {
		this.unitUsed = unitUsed;
	}
	
	
	
}
