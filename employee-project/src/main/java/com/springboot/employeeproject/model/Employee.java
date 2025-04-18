package com.springboot.employeeproject.model;


import jakarta.persistence.*;
import lombok.*;

import java.util.List;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Employee {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    private int age;

    private int salary;

    @OneToMany(mappedBy = "employee" , cascade = CascadeType.ALL  , orphanRemoval = true)
    private List<Email> email;

    public Employee(Long id, String name, int age, int salary) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.salary = salary;
    }
}
