package com.kenzan.service.employeeverse.model;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private final Date dateOfBirth;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private final Date dateOfEmployment;
    private Status status;

    public enum Status {
        ACTIVE("A"),
        INACTIVE("I");

        private String value;

        Status(String value) {

            this.value = value;
        }

        public static Status fromValue(String value) {


            for(Status status : values()) {

                if(status.value.equalsIgnoreCase(value)) {

                    return status;
                }
            }

            //If it is a different String, then take it as an ACTIVE status as this is the default value.
            return ACTIVE;

        }
    }


    public Employee(@JsonProperty("id") UUID id, @JsonProperty("firstName") String firstName,
                    @JsonProperty("middleInitial") String middleInitial, @JsonProperty("lastName") String lastName,
                    @JsonProperty("dateOfBirth") Date dateOfBirth, @JsonProperty("dateOfEmployment") Date dateOfEmployment,
                    @JsonProperty("status") Status status) {

        this.id =id;
        this.firstName = firstName;
        this.middleInitial = middleInitial;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.dateOfEmployment = dateOfEmployment;
        this.status = status;

    }

    public void setStatus(Status status) {

        this.status = status;

    }

    public Status getStatus() {

        return status;
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
