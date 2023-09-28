package com.example.InputAllocation.Login;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
	@Autowired
	private LoginRepository loginRepository;
	private static final Logger logger=LoggerFactory.getLogger(LoginService.class);
	
	public Map<String,Object> login(LoginPojo loginPojo) {
		Map<String,Object> m=new HashMap<>();
		try {
			if(loginRepository.login(loginPojo)==1) {
				m.put("message", loginPojo.getUsername()+" logged in successful");
				m.put("flag", true);
				m.put("status", 1);
				logger.debug(loginPojo.getUsername()+" logged in successful");
				return m;
			}
			m.put("message","Invalid User Credentials");
			m.put("flag", true);
			m.put("status", 0);
			logger.debug(loginPojo.getUsername()+" Invalid User Credentials");
		}catch(Exception e){
			e.printStackTrace();
			m.put("message","Error while login");
			m.put("flag", false);
			m.put("status", 0);
		}
		return m;
	}
}
