package com.springboot.employeeproject.dao;

import com.springboot.employeeproject.dto.EmployeeDTO;
import com.springboot.employeeproject.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeDAO extends JpaRepository<Employee, Long> {


    @Query("SELECT DISTINCT e FROM Employee e  JOIN  e.email")
    List<Employee> findAllWithEmails();

    @Query("SELECT e FROM Employee e LEFT JOIN FETCH e.email WHERE e.id = :id")
    Optional<Employee> findByIdWithEmails(@Param("id") Long id);


    List<Employee> findByNameIn(List<String> name);

    @Query("SELECT new com.springboot.employeeproject.model.Employee(e.id, e.name, e.age, e.salary) FROM Employee e")
    List<Employee> findAllEmployee();
}
