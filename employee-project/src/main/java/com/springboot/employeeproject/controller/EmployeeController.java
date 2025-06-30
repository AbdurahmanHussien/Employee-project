package com.springboot.employeeproject.controller;


import com.springboot.employeeproject.dto.BundleMessageDTO;
import com.springboot.employeeproject.dto.EmployeeDTO;
import com.springboot.employeeproject.service.employee.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.transaction.SystemException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private EmployeeService employeeService;
    private final MessageSource messageSource;


    @Autowired
    public EmployeeController(EmployeeService employeeService , MessageSource messageSource) {
        this.employeeService = employeeService;
        this.messageSource = messageSource;
    }

    @GetMapping("employee")
    @Operation(summary = "Get all employees")
    public ResponseEntity  <List<EmployeeDTO>> getAllEmployees() {
        return ResponseEntity.ok(employeeService.getAllEmployees());
    }
    @GetMapping("/with-emails")
    @Operation(summary = "Get all employees with emails")
    public ResponseEntity  <List<EmployeeDTO>> getAllEmployeesWithEmail() {
        return ResponseEntity.ok(employeeService.getAllEmployeesWithEmails());
    }

    @PostMapping("/employee")
    @Operation(summary = "Create employee")
    public ResponseEntity  <EmployeeDTO>  createEmployee(@RequestBody @Valid EmployeeDTO employeeDTO) throws SystemException {
        return ResponseEntity.ok(employeeService.createEmployee(employeeDTO));
    }

    @PutMapping("/employee")
    @Operation(summary = "Update employee")
    public ResponseEntity  <EmployeeDTO>  updateEmployee(@RequestBody @Valid EmployeeDTO employeeDTO) throws SystemException {
        return ResponseEntity.ok(employeeService.updateEmployee(employeeDTO));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Delete employee")
    public ResponseEntity  <BundleMessageDTO>  deleteEmployee(@PathVariable Long id ) throws SystemException {

        return employeeService.deleteEmployee(id);
    }
    @GetMapping("/{id}")
    @Operation(summary = "Get employee by id")
    public ResponseEntity <EmployeeDTO> getEmployeeById( @PathVariable long id) throws SystemException {
        return ResponseEntity.ok(employeeService.findEmployeeById(id));
    }

    @GetMapping("/search/by-ids")
    @Operation(summary = "Get employees by ids")
    //GET /employees/search/by-ids?ids=1,2,3
    public ResponseEntity<List<EmployeeDTO>> getEmployeesByIds(@RequestParam(name = "ids") List<Long> ids) throws SystemException {
        List<EmployeeDTO> employees = employeeService.getEmployeesByListOfId(ids);
        return ResponseEntity.ok(employees);
    }

    @GetMapping("/search/by-names")
    @Operation(summary = "Get employees by names")
    //GET /employees/search/by-names?names=  ,  ,
    public ResponseEntity<List<EmployeeDTO>> getEmployeesByNames(@RequestParam List<String> names) throws SystemException {
        List<EmployeeDTO> employees = employeeService.getEmployeesByListOfName(names);
        return ResponseEntity.ok(employees);
    }



}
