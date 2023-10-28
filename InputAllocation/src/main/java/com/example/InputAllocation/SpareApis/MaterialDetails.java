package com.example.InputAllocation.SpareApis;

public class MaterialDetails {
	private String therapeuticGroup;
	private String brand;
	private String customerName;
	private String customerId;
	private String materialName;
	private int saleQuantity;
	private int saleAmount;
	private int returnQuantity;
	private int returnAmount;
	private int netAmount;
	private int netQuantity;
	
	public String getTherapeuticGroup() {
		return therapeuticGroup;
	}
	public void setTherapeuticGroup(String therapeuticGroup) {
		this.therapeuticGroup = therapeuticGroup;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public String getMaterialName() {
		return materialName;
	}
	public void setMaterialName(String materialName) {
		this.materialName = materialName;
	}
	public int getSaleQuantity() {
		return saleQuantity;
	}
	public void setSaleQuantity(int saleQuantity) {
		this.saleQuantity = saleQuantity;
	}
	public int getSaleAmount() {
		return saleAmount;
	}
	public void setSaleAmount(int saleAmount) {
		this.saleAmount = saleAmount;
	}
	public int getReturnQuantity() {
		return returnQuantity;
	}
	public void setReturnQuantity(int returnQuantity) {
		this.returnQuantity = returnQuantity;
	}
	public int getReturnAmount() {
		return returnAmount;
	}
	public void setReturnAmount(int returnAmount) {
		this.returnAmount = returnAmount;
	}
	public int getNetAmount() {
		return netAmount;
	}
	public void setNetAmount(int netAmount) {
		this.netAmount = netAmount;
	}
	public int getNetQuantity() {
		return netQuantity;
	}
	public void setNetQuantity(int netQuantity) {
		this.netQuantity = netQuantity;
	}
	@Override
	public String toString() {
		return "MaterialDetails [therapeuticGroup=" + therapeuticGroup + ", brand=" + brand + ", customerName="
				+ customerName + ", customerId=" + customerId + ", materialName=" + materialName + ", saleQuantity="
				+ saleQuantity + ", saleAmount=" + saleAmount + ", returnQuantity=" + returnQuantity + ", returnAmount="
				+ returnAmount + ", netAmount=" + netAmount + ", netQuantity=" + netQuantity + "]";
	}
}
