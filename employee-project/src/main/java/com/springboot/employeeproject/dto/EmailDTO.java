package com.springboot.employeeproject.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.springboot.employeeproject.model.Employee;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;



@JsonInclude(JsonInclude.Include.NON_NULL)

public class EmailDTO {

    private Long id;

    @NotEmpty(message = "Enter the type of email")
    private String name;

    @Email(message = "error.type.invalid")
    @Column(unique = true)
    private String value;

    @NotNull(message = "employee ID is required")
    @JsonProperty("employee_id")
    private Long employeeId;

    private Employee employee;

   public EmailDTO(Long id, String name, String value) {
        this.id = id;
        this.name = name;
        this.value = value;
    }
    public EmailDTO(String value) {
        this.value = value;
    }
    public EmailDTO() {}


    public EmailDTO(Long id, String name, String value, Long employeeId, Employee employee) {
        this.id = id;
        this.name = name;
        this.value = value;
        this.employeeId = employeeId;
        this.employee = employee;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotEmpty(message = "Enter the type of email") String getName() {
        return name;
    }

    public void setName(@NotEmpty(message = "Enter the type of email") String name) {
        this.name = name;
    }

    public @Email(message = "error.type.invalid") String getValue() {
        return value;
    }

    public void setValue(@Email(message = "error.type.invalid") String value) {
        this.value = value;
    }

    public @NotNull(message = "employee ID is required") Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(@NotNull(message = "employee ID is required") Long employeeId) {
        this.employeeId = employeeId;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}


