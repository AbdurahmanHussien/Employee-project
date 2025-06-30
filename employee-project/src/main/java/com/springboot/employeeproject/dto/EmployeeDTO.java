package com.springboot.employeeproject.dto;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.modelmapper.internal.bytebuddy.implementation.bind.annotation.Default;

import java.util.List;



@JsonInclude(JsonInclude.Include.NON_NULL)
public class EmployeeDTO {


        private Long id;

        @NotBlank(message = "enter.your.name")
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

    public EmployeeDTO(Long id, String name, int age, int salary, List<EmailDTO> email) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.salary = salary;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public  String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Min(15)
    @Max(40)
    public int getAge() {
        return age;
    }

    public void setAge( int age) {
        this.age = age;
    }


    public int getSalary() {
        return salary;
    }

    public void setSalary( int salary) {
        this.salary = salary;
    }

    public List<EmailDTO> getEmail() {
        return email;
    }

    public void setEmail(List<EmailDTO> email) {
        this.email = email;
    }
}

