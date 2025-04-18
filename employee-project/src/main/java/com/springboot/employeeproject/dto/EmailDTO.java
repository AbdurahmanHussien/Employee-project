package com.springboot.employeeproject.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.springboot.employeeproject.model.Employee;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;


@Getter
@Setter
@AllArgsConstructor
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

    public EmailDTO(Long id, String name, String value, long employeeId) {
       this.id = id;
       this.name = name;
       this.value = value;
       this.employeeId = employeeId;

    }
}
