package com.kenzan.service.employeeverse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class EmployeeverseApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmployeeverseApplication.class, args);
	}

}
