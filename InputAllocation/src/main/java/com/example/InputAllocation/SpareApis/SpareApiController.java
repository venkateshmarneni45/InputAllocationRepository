package com.example.InputAllocation.SpareApis;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/allocate")
public class SpareApiController {
	@Autowired
	private SpareApiService spareApiService;
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private HttpServletRequest request;

	@PostMapping(value = "/divisions", consumes = "multipart/form-data", produces = "application/json")
	public Map<String, Object> getDivisions(@RequestParam String username) {
		jdbcTemplate.update("Insert into tbl_user_activities(user,api) values(?,?)",
				new Object[] { username, request.getRequestURI().toString() });
		return spareApiService.getDivisions(username);
	}

	@PostMapping(value = "/hqs", consumes = "application/json", produces = "application/json")
	public Map<String, Object> getHeaQuarters(@RequestBody Map<String, Object> data) {
		jdbcTemplate.update("Insert into tbl_user_activities(user,api) values(?,?)",
				new Object[] { data.get("username"), request.getRequestURI().toString() });
		return spareApiService.getHeaQuarters(data);
	}

	@PostMapping(value = "/saleperhq", consumes = "application/json", produces = "application/json")
	public Map<String, Object> getSalesData(@RequestBody Map<String, Object> data) {
		jdbcTemplate.update("Insert into tbl_user_activities(user,api) values(?,?)",
				new Object[] { data.get("username"), request.getRequestURI().toString() });
		return spareApiService.getSalesData(data);
	}

	@PostMapping(value = "/materialsaledata", consumes = "application/json", produces = "application/json")
	public Map<String, Object> getMaterialData(@RequestBody Map<String, Object> data) {
		jdbcTemplate.update("Insert into tbl_user_activities(user,api) values(?,?)",
				new Object[] { data.get("username"), request.getRequestURI().toString() });
		return spareApiService.getMaterialData(data);
	}

	@PostMapping(value = "/allocateinputs", consumes = "application/json", produces = "application/json")
	public Map<String, Object> allocateInputsPerHq(@RequestBody Map<String, Object> data) {
		jdbcTemplate.update("Insert into tbl_user_activities(user,api) values(?,?)",
				new Object[] { data.get("username"), request.getRequestURI().toString() });
		return spareApiService.allocateInputsPerHq(data);
	}

	@PostMapping(value = "/fetchinputsdata", consumes = "application/json", produces = "application/json")
	public Map<String, Object> fetchInputMaterials(@RequestBody Map<String, Object> data) {
		jdbcTemplate.update("Insert into tbl_user_activities(user,api) values(?,?)",
				new Object[] { data.get("username"), request.getRequestURI().toString() });
		return spareApiService.fetchInputMaterials();
	}

	@PostMapping(value = "/updateinputsdata", consumes = "application/json", produces = "application/json")
	public Map<String, Object> updateInputMaterials(@RequestBody Map<String, Object> data) {
		jdbcTemplate.update("Insert into tbl_user_activities(user,api) values(?,?)",
				new Object[] { data.get("username"), request.getRequestURI().toString() });
		return spareApiService.updateInputMaterials(data);
	}

	@PostMapping(value = "/updateallocatedinputs", consumes = "application/json", produces = "application/json")
	public Map<String, Object> updateAllocatedInputs(@RequestBody Map<String, Object> data) {
		jdbcTemplate.update("Insert into tbl_user_activities(user,api) values(?,?)",
				new Object[] { data.get("username"), request.getRequestURI().toString() });
		return spareApiService.updateAllocatedInputs(data);
	}

	@PostMapping(value = "/sendmail", consumes = "application/json", produces = "application/json")
	public Map<String, Object> mailSender(@RequestBody Map<String, Object> data) {
		jdbcTemplate.update("Insert into tbl_user_activities(user,api) values(?,?)",
				new Object[] { data.get("username"), request.getRequestURI().toString() });
		return spareApiService.mailSender(data);
	}
}
