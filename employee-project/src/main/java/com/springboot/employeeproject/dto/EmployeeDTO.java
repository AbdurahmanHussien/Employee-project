package com.springboot.employeeproject.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.modelmapper.internal.bytebuddy.implementation.bind.annotation.Default;

import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EmployeeDTO {


        private Long id;

        @NotEmpty(message = "Enter your name")
        private String name;

        @Min(15)
        @Max(40)
        private int age;

         @Min(5000)
         @Max(10000)
        private int salary;

        private List<EmailDTO> email;

      public EmployeeDTO(Long id, String name , List<EmailDTO> email) {
            this.id = id;
            this.name = name;
            this.email = email;
        }

    public EmployeeDTO(Long id, String name , int age , int salary) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.salary = salary;

    }

    public EmployeeDTO() {}

}

