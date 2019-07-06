package com.kenzan.service.employeeverse;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kenzan.service.employeeverse.model.Employee;
import com.kenzan.service.employeeverse.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class EmployeeverseApplication {

	@Autowired
	private Environment environment;

	public static void main(String[] args) {
		SpringApplication.run(EmployeeverseApplication.class, args);
	}

	@Bean
	CommandLineRunner runner(EmployeeService employeeService) {

		return args -> {

			//Get external file and load it into the repository.
			ObjectMapper mapper = new ObjectMapper();
			TypeReference<List<Employee>> typeReference = new TypeReference<List<Employee>>() {

			};

			//Get external file path
			InputStream inputStream = TypeReference.class.getResourceAsStream(environment.getProperty("external.file.path"));
			try {

				List<Employee> employees = mapper.readValue(inputStream, typeReference);
				System.out.println("Length of employees universe: " + employees.size());
				employeeService.save(employees);
				System.out.println("Employees loaded!");

			} catch(IOException exception) {

				System.out.println("Unable to get external file: " + exception.getMessage());
			} catch(Exception exception) {

				System.out.println("Something else went wrong! " + exception.getMessage());
			}

		};
	}

}
