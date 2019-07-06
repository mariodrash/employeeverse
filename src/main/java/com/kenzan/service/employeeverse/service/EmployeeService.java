package com.kenzan.service.employeeverse.service;

import com.kenzan.service.employeeverse.model.Employee;
import com.kenzan.service.employeeverse.repository.EmployeeRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * @author mrodz
 * Service to contain business logic of Employee API in order to perform several tasks such as:
 * Getting, Creating and Deleting data from {@link com.kenzan.service.employeeverse.repository.EmployeeRepository}, which may vary according to the implementation of the repository.
 */
@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private static final Logger logger = LogManager.getLogger(EmployeeService.class);

    @Autowired
    public EmployeeService(@Qualifier("onMemRepo") EmployeeRepository employeeRepository) {

        this.employeeRepository = employeeRepository;
    }

    public List<Employee> getAllEmployees(){

        return employeeRepository.getAll();
    }

    public void save(List<Employee> employees) {

        employeeRepository.addEmployees(employees);
    }

    public Optional<Employee> getEmployeeById(UUID id) {

        return employeeRepository.findEmployeeById(id);
    }

    public void addEmployee(Employee employee) {

        employeeRepository.addEmployee(employee);
    }

    public int updateEmployee(UUID id, Employee employee) {

        return employeeRepository.updateEmployeeById(id, employee);
    }

    public int deleteEmployeeById(UUID id) {

        Optional<Employee> optionalEmployee = employeeRepository.findEmployeeById(id);
        logger.info("Entering after getting by Id...");
        Employee employee;
        int status = 0;
        if(optionalEmployee.isPresent()) {

            employee = optionalEmployee.get();
            employee.setStatus(Employee.Status.INACTIVE);

            status = employeeRepository.updateEmployeeById(id, employee);
        }

        return status;
    }

}
