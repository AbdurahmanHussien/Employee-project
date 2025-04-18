package com.springboot.employeeproject.controller;


import com.springboot.employeeproject.dto.EmployeeDTO;
import com.springboot.employeeproject.service.employee.EmployeeService;
import jakarta.transaction.SystemException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/Employees")
public class EmployeeController {

    private EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public ResponseEntity  <List<EmployeeDTO>> getAllEmployees() {
        return ResponseEntity.ok(employeeService.getAllEmployees());
    }
    @GetMapping("/withEmail")
    public ResponseEntity  <List<EmployeeDTO>> getAllEmployeesWithEmail() {
        return ResponseEntity.ok(employeeService.getAllEmployeesWithEmails());
    }

    @PostMapping("/createEmployee")
    public ResponseEntity  <EmployeeDTO>  createEmployee(@RequestBody @Valid EmployeeDTO employeeDTO) throws SystemException {
        return ResponseEntity.created(URI.create("/createEmployee")).body(employeeService.createEmployee(employeeDTO));
    }

    @PutMapping("/updateEmployee")
    public ResponseEntity  <EmployeeDTO>  updateEmployee(@RequestBody @Valid EmployeeDTO employeeDTO) throws SystemException {
        return ResponseEntity.ok(employeeService.updateEmployee(employeeDTO));
    }

    @DeleteMapping("/deleteEmployee/{id}")
    public ResponseEntity  <String>  deleteEmployee(@PathVariable Long id )  {
        try {
            employeeService.deleteEmployee(id);
            return ResponseEntity.ok().build();
        } catch (RuntimeException | SystemException e) {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity <EmployeeDTO> getEmployeeById( @PathVariable long id) throws SystemException {
        return ResponseEntity.ok(employeeService.findEmployeeById(id));
    }

    @GetMapping("/by-ids")
    //GET /employees/by-ids?ids=1,2,3
    public ResponseEntity<List<EmployeeDTO>> getEmployeesByIds(@RequestParam List<Long> ids) throws SystemException {
        List<EmployeeDTO> employees = employeeService.getEmployeesByListOfId(ids);
        return ResponseEntity.ok(employees);
    }

    @GetMapping("/by-names")
    //GET /employees/by-names?names=  ,  ,
    public ResponseEntity<List<EmployeeDTO>> getEmployeesByNames(@RequestParam List<String> names) throws SystemException {
        List<EmployeeDTO> employees = employeeService.getEmployeesByListOfName(names);
        return ResponseEntity.ok(employees);
    }



}
