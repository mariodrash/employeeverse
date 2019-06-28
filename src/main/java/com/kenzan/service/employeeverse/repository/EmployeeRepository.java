package com.kenzan.service.employeeverse.repository;

import com.kenzan.service.employeeverse.model.Employee;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 *
 * @author mrodz
 *
 * Interface to be extended by the different Employee Repositories in order to get or create new employee records.
 *
 */
public interface EmployeeRepository {

    /**
     * Get all the employees from the universe in the current repo 🖥
     * @return Employee Universe ordered by First Name.
     */
    List<Employee> getAll();

    /**
     * Persists into the Repository a collection of employees.
     * This method could be used in order to load pre existing records from a file.
     * @param employees List of employees contained in a configuration file in order to be loaded in the startup.
     */
    void addEmployees(List<Employee> employees);

    /**
     * Add a new employee in the current repo 📖
     * @param id Id of the Employee to be added.
     * @param employee Object of type{@link Employee} which contains all the current attributes that define an employee
     */
    void addEmployee(UUID id, Employee employee);

    /**
     * Default method to add a new Employee to the current repository. Internally it generates an Id of type {@link UUID}.
     * @param employee Object of type{@link Employee} which contains all the current attributes that define an employee
     */
    default void addEmployee(Employee employee) {

        final UUID id = UUID.randomUUID();
        addEmployee(id, employee);
    }

    /**
     * Deletes an employee record in the current repo by Id field.
     * @param id Id of the record to be deleted from the repository
     */
    void deleteEmployeeById(UUID id);

    /**
     * Updates an employee record in the current repo by Id field.
     * @param id Id of the record to be updated from the repository.
     */
    void updateEmployeeById(UUID id);

    /**
     * Gets an employee record in the current repo by Id field.
     * @param id Id of the record to be retrieved from the repository.
     * @return an {@link Optional} describing the employee element from the repository with the current id, or an empty {@link Optional} if there are any matches.
     */
    Optional<Employee> findEmployeeById(UUID id);



}
