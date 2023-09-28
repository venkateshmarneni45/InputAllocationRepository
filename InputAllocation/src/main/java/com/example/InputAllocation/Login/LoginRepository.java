package com.example.InputAllocation.Login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class LoginRepository {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public int login(LoginPojo loginPojo) {
		String query = "select count(*) from hclhrm_prod.tbl_employee_login where employeecode="
				+ loginPojo.getUsername() + " and password=md5('" + loginPojo.getPassword() + "') and status=1001";
		return jdbcTemplate.queryForObject(query, Integer.class);
	}
}
