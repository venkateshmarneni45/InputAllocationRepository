package com.example.InputAllocation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class InputAllocationApplication {

	public static void main(String[] args) {
		SpringApplication.run(InputAllocationApplication.class, args);
	}
}
