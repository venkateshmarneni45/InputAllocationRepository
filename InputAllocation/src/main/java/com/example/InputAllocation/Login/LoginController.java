package com.example.InputAllocation.Login;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {
	@Autowired
	private LoginService loginService;

	@PostMapping(value = "/userlogin", consumes = "application/json", produces = "application/json")
	public Map<String, Object> login(@RequestBody LoginPojo loginPojo) {
		return loginService.login(loginPojo);
	}

}
