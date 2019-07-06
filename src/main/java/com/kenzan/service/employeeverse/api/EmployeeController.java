package com.kenzan.service.employeeverse.api;

import com.kenzan.service.employeeverse.model.Employee;
import com.kenzan.service.employeeverse.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

/**
 * @author mrodz
 *
 * Controller to map endpoints according to the received requests in the Employeeverse API.
 *
 * Employee API allows a client to:
 * <p>Get all the employees in the current repository. See {@link #getAllEmployees()}</p>
 * <p>Add an employee considering all the current attributes that defines an employee. See {@link #addEmployee(Employee)}</p>
 * <p>Retrieve an employee by Id. See {@link #getEmployeeById(UUID)}</p>
 * <p>Delete an employee by Id. See {@link #deleteEmployeeById(UUID)}</p>
 * <p>Update an employee by Id. See {@link #updateEmployeeById(UUID, Employee)}</p>
 */
@RequestMapping("api/v1/employee")
@RestController
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {

        this.employeeService = employeeService;
    }

    /**
     * Add a new Employee to the universe.
     * @param employee Employee to be added to the repository, all the attributes that defines an employee needs to be present.
     */
    @PostMapping
    public void addEmployee(@Valid @NotNull @RequestBody Employee employee) {

        employeeService.addEmployee(employee);
    }

    /**
     * Retrieves all the employees in the current universe. Only employeed with ACTIVE status are retrieved.
     * @return List of available employees, an empty list if there is any.
     */
    @GetMapping
    public List<Employee> getAllEmployees() {

        return employeeService.getAllEmployees();
    }

    /**
     * Retrieves an employee from the current Repo by Id field.
     * @param id Employee Identifier to retrieve an employee from the universe.
     * @return Employee that matches the specified Id, null if there is any matching result.
     */
    @GetMapping(path = "{id}")
    public Employee getEmployeeById(@PathVariable("id") UUID id) {

        return employeeService.getEmployeeById(id)
                .orElse(null);
    }

    /**
     * Deletes an employee from the current Repo by Id field. The record is not deleted, it only sets the Status field from ACTIVE to INACTIVE.
     * @param id Employee Identifier to delete an employee from the employee universe.
     */
    @DeleteMapping(path = "{id}")
    public void deleteEmployeeById(@PathVariable("id") UUID id) {

        employeeService.deleteEmployeeById(id);
    }

    /**
     * Updates an employee record from the current repo by Id.
     * @param id Employee Identifier to update an employee from the universe.
     * @param employee Employee record containing updated fields.
     */
    @PutMapping(path = "{id}")
    public void updateEmployeeById(@PathVariable("id") UUID id, @Valid @NotNull @RequestBody Employee employee) {

        employeeService.updateEmployee(id, employee);
    }

}
