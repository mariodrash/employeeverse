package com.kenzan.service.employeeverse.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.UUID;

/***
 * @author mrodz
 *
 * Class to represent an Employee and to be managed by the Employee API
 */
public class Employee {

    private final UUID id;
    @NotBlank
    private final String firstName;
    @Size(max = 1)
    private final String middleInitial;
    private final String lastName;
    private final Date dateOfBirth;
    private final Date dateOfEmployment;
    private Status status;

    public enum Status {
        ACTIVE,
        INACTIVE
    }


    public Employee(@JsonProperty("id") UUID id, @JsonProperty("firstName") String firstName,
                    @JsonProperty("middleInitial") String middleInitial, @JsonProperty("lastName") String lastName,
                    @JsonProperty("dateOfBirth") Date dateOfBirth, @JsonProperty("dateOfEmployment") Date dateOfEmployment,
                    String statusStr) {

        this.id =id;
        this.firstName = firstName;
        this.middleInitial = middleInitial;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.dateOfEmployment = dateOfEmployment;
        setStatus(statusStr);

    }

    @JsonProperty("status")
    public void setStatus(String statusStr) {

        this.status = "ACTIVE".equals(statusStr) ? Status.ACTIVE : Status.INACTIVE;

    }

    @JsonProperty("status")
    public String getStatus(Status status) {

        switch (status) {

            case ACTIVE:
                return "ACTIVE";

                default:
                    return "INACTIVE";

        }

    }

    public UUID getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getMiddleInitial() {
        return middleInitial;
    }

    public String getLastName() {
        return lastName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public Date getDateOfEmployment() {
        return dateOfEmployment;
    }


}
