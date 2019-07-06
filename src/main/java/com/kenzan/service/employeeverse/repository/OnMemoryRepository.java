package com.kenzan.service.employeeverse.repository;

import com.kenzan.service.employeeverse.model.Employee;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * @author mrodz
 *
 * Implementation in memory of {@link EmployeeRepository}.
 *
 * All data associated to employees are persisted just in-memory level. The records only exists as the application is ruynning and are removed in the shutdown.
 */
@Repository("onMemRepo")
public class OnMemoryRepository  implements EmployeeRepository {

    //Static member to persist movies in memory level.
    private static List<Employee> employees = new ArrayList<>();

    /**
     * {@inheritDoc}
     * @return Employees Universe in memory
     */
    @Override
    public List<Employee> getAll() {

        return employees.stream()
                .filter(employee -> Employee.Status.ACTIVE.equals(employee.getStatus()))
                .collect(Collectors.toList());
    }

    /**
     *
     * {@inheritDoc}
     * @param employees List of employees contained in a configuration file in order to be loaded in the startup.
     */
    @Override
    public void addEmployees(List<Employee> employees) {

        this.employees = employees;
    }

    /**
     * {@inheritDoc}
     * @param id Id of the Employee to be added.
     * @param employee Object of type{@link Employee} which contains all the current attributes that define an employee
     */
    @Override
    public void addEmployee(UUID id, Employee employee) {

        employees.add(new Employee(id, employee.getFirstName(), employee.getMiddleInitial(), employee.getLastName(), employee.getDateOfBirth()
        , employee.getDateOfEmployment(), employee.getStatus()));
    }

    /**
     * {@inheritDoc}
     * @param id Id of the record to be retrieved from the repository.
     * @return an {@link Optional} describing the movie elememt from the repository with the current Id,
     *      *      * or an empty {@link Optional} if there are any element.
     */
    @Override
    public Optional<Employee> findEmployeeById(UUID id) {

        return employees.stream()
                .filter(employee -> employee.getId().equals(id)
                && (Employee.Status.ACTIVE.equals(employee.getStatus())))
                .findFirst();
    }

    /**
     * {@inheritDoc}
     * @param id Id of the record to be updated from the repository.
     * @param employee Employee object to be updated in the current repo.
     * @return @return int type, 0 if the record was not able to be updated. 1 if it succeed.
     */
    @Override
    public int updateEmployeeById(UUID id, Employee employee) {

        return findEmployeeById(id)
                .map( e -> {
                    int indexOfEmployeeToUpdate = employees.indexOf(e);

                    if(indexOfEmployeeToUpdate >= 0) {

                        employees.set(indexOfEmployeeToUpdate, new Employee(id, employee.getFirstName(),
                                employee.getMiddleInitial(), employee.getLastName(), employee.getDateOfBirth(),
                                employee.getDateOfEmployment(), employee.getStatus()));
                        return 1;
                    }

                    return 0;
                })
                .orElse(0);

    }



}
