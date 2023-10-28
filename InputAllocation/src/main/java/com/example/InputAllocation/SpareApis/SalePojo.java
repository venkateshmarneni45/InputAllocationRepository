package com.example.InputAllocation.SpareApis;

public class SalePojo {
	private String hqcode;
	private String hqname;
	private String materialcode;
	private String materialname;
	private double previousmonthsales;
	private int previousmonth;
	private int lastmonth;
	private double lastmonthsales;
	private int tsecount;
	private double phpm;

	public String getHqcode() {
		return hqcode;
	}

	public void setHqcode(String hqcode) {
		this.hqcode = hqcode;
	}

	public String getHqname() {
		return hqname;
	}

	public void setHqname(String hqname) {
		this.hqname = hqname;
	}

	public String getMaterialcode() {
		return materialcode;
	}

	public void setMaterialcode(String materialcode) {
		this.materialcode = materialcode;
	}

	public String getMaterialname() {
		return materialname;
	}

	public void setMaterialname(String materialname) {
		this.materialname = materialname;
	}

	public double getPreviousmonthsales() {
		return previousmonthsales;
	}

	public void setPreviousmonthsales(double previousmonthsales) {
		this.previousmonthsales = previousmonthsales;
	}

	public int getPreviousmonth() {
		return previousmonth;
	}

	public void setPreviousmonth(int previousmonth) {
		this.previousmonth = previousmonth;
	}

	public int getLastmonth() {
		return lastmonth;
	}

	public void setLastmonth(int lastmonth) {
		this.lastmonth = lastmonth;
	}

	public double getLastmonthsales() {
		return lastmonthsales;
	}

	public void setLastmonthsales(double lastmonthsales) {
		this.lastmonthsales = lastmonthsales;
	}

	public int getTsecount() {
		return tsecount;
	}

	public void setTsecount(int tsecount) {
		this.tsecount = tsecount;
	}

	public double getPhpm() {
		return phpm;
	}

	public void setPhpm(double phpm) {
		this.phpm = phpm;
	}

	@Override
	public String toString() {
		return "SalePojo [hqcode=" + hqcode + ", hqname=" + hqname + ", materialcode=" + materialcode
				+ ", materialname=" + materialname + ", previousmonthsales=" + previousmonthsales + ", previousmonth="
				+ previousmonth + ", lastmonth=" + lastmonth + ", lastmonthsales=" + lastmonthsales + ", tsecount="
				+ tsecount + ", phpm=" + phpm + "]";
	}
}
