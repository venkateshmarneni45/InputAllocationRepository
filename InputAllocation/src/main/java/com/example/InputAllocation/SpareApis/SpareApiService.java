package com.example.InputAllocation.SpareApis;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SpareApiService {
	@Autowired
	private SpareApiRepository spareApiRepository;

	public Map<String, Object> getDivisions(String username) {
		Map<String, Object> m = new HashMap<>();
		try {
			m.put("message", spareApiRepository.getDivisions(username));
			m.put("flag", true);
			m.put("status", 1);
		} catch (Exception e) {
			e.printStackTrace();
			m.put("message", "Error fetching divisions");
			m.put("flag", false);
			m.put("status", 0);
		}
		return m;
	}

	public Map<String, Object> getHeaQuarters(Map<String, Object> data) {
		Map<String, Object> m = new HashMap<>();
		try {
			m.put("message", spareApiRepository.getHeaQuarters(data));
			m.put("flag", true);
			m.put("status", 1);
		} catch (Exception e) {
			e.printStackTrace();
			m.put("message", "Error fetching head quarters");
			m.put("flag", false);
			m.put("status", 0);
		}
		return m;
	}

	public Map<String, Object> getSalesData(Map<String, Object> data) {
		Map<String, Object> m = new HashMap<>();
		try {
			m.put("message", spareApiRepository.getSalesData(data));
			m.put("flag", true);
			m.put("status", 1);
		} catch (Exception e) {
			e.printStackTrace();
			m.put("message", "Error fetching sales data");
			m.put("flag", false);
			m.put("status", 0);
		}
		return m;
	}

	public Map<String, Object> getMaterialData(Map<String, Object> data) {
		Map<String, Object> m = new HashMap<>();
		try {
			m.put("message", spareApiRepository.getMaterialData(data));
			m.put("flag", true);
			m.put("status", 1);
		} catch (Exception e) {
			e.printStackTrace();
			m.put("message", "Error fetching material data");
			m.put("flag", false);
			m.put("status", 0);
		}
		return m;
	}

	public Map<String, Object> allocateInputsPerHq(Map<String, Object> data) {
		Map<String, Object> m = new HashMap<>();
		try {
			m.put("message", spareApiRepository.allocateInputsPerHq(data));
			m.put("flag", true);
			m.put("status", 1);
		} catch (Exception e) {
			e.printStackTrace();
			m.put("message", "Error fetching allocated inputs");
			m.put("flag", false);
			m.put("status", 0);
		}
		return m;
	}

	public Map<String, Object> fetchInputMaterials() {
		Map<String, Object> m = new HashMap<>();
		try {
			m.put("message", spareApiRepository.fetchInputMaterials());
			m.put("flag", true);
			m.put("status", 1);
		} catch (Exception e) {
			e.printStackTrace();
			m.put("message", "Error fetching input materials");
			m.put("flag", false);
			m.put("status", 0);
		}
		return m;
	}

	public Map<String, Object> updateInputMaterials(Map<String, Object> data) {
		Map<String, Object> m = new HashMap<>();
		try {
			m.put("message", "No.of inputs updated are " + spareApiRepository.updateInputMaterials(data));
			m.put("flag", true);
			m.put("status", 1);
		} catch (Exception e) {
			e.printStackTrace();
			m.put("message", "Error updating input materials");
			m.put("flag", false);
			m.put("status", 0);
		}
		return m;
	}

	public Map<String, Object> updateAllocatedInputs(Map<String, Object> data) {
		Map<String, Object> m = new HashMap<>();
		try {
			spareApiRepository.updateAllocatedInputs(data);
			m.put("message", "Allocated Inputs updated for SE");
			m.put("flag", true);
			m.put("status", 1);
		} catch (Exception e) {
			e.printStackTrace();
			m.put("message", "Error updating allocated inputs");
			m.put("flag", false);
			m.put("status", 0);
		}
		return m;
	}

	public Map<String, Object> mailSender(Map<String, Object> data) {
		Map<String, Object> m = new HashMap<>();
		try {
			spareApiRepository.mailSender(data);
			m.put("message", "Mail sent succesfull");
			m.put("flag", true);
			m.put("status", 1);
		} catch (Exception e) {
			e.printStackTrace();
			m.put("message", "Error sending mail");
			m.put("flag", false);
			m.put("status", 0);
		}
		return m;
	}

}
