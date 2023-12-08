package com.example.InputAllocation.SpareApis;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SaleExecutives {
	private String EmpCode;
	private String EmpName;
	private String HQCode;
	private List<Map<String, Object>> inputs = new ArrayList<>();

	public String getEmpCode() {
		return EmpCode;
	}

	public void setEmpCode(String empCode) {
		EmpCode = empCode;
	}

	public String getEmpName() {
		return EmpName;
	}

	public void setEmpName(String empName) {
		EmpName = empName;
	}

	public String getHQCode() {
		return HQCode;
	}

	public void setHQCode(String hQCode) {
		HQCode = hQCode;
	}

	public List<Map<String, Object>> getInputs() {
		return inputs;
	}

	public void setInputs(List<Map<String, Object>> inputs) {
		this.inputs = inputs;
	}

	@Override
	public String toString() {
		return "SaleExecutives [EmpCode=" + EmpCode + ", EmpName=" + EmpName + ", HQCode=" + HQCode + ", inputs="
				+ inputs + "]";
	}
}
