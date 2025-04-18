package com.springboot.employeeproject.controller;


import com.springboot.employeeproject.dto.BundleMessageDTO;
import com.springboot.employeeproject.dto.EmployeeDTO;
import com.springboot.employeeproject.service.bundleService.BundleTranslatorService;
import com.springboot.employeeproject.service.employee.EmployeeService;
import jakarta.transaction.SystemException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final BundleTranslatorService bundleTranslatorService;
    private EmployeeService employeeService;
    private final MessageSource messageSource;


    @Autowired
    public EmployeeController(EmployeeService employeeService, BundleTranslatorService bundleTranslatorService , MessageSource messageSource) {
        this.employeeService = employeeService;
        this.bundleTranslatorService = bundleTranslatorService;
        this.messageSource = messageSource;
    }

    @GetMapping
    public ResponseEntity  <List<EmployeeDTO>> getAllEmployees() {
        return ResponseEntity.ok(employeeService.getAllEmployees());
    }
    @GetMapping("/with-emails")
    public ResponseEntity  <List<EmployeeDTO>> getAllEmployeesWithEmail() {
        return ResponseEntity.ok(employeeService.getAllEmployeesWithEmails());
    }

    @PostMapping
    public ResponseEntity  <EmployeeDTO>  createEmployee(@RequestBody @Valid EmployeeDTO employeeDTO) throws SystemException {
        return ResponseEntity.ok(employeeService.createEmployee(employeeDTO));
    }

    @PutMapping
    public ResponseEntity  <EmployeeDTO>  updateEmployee(@RequestBody @Valid EmployeeDTO employeeDTO) throws SystemException {
        return ResponseEntity.ok(employeeService.updateEmployee(employeeDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity  <BundleMessageDTO>  deleteEmployee(@PathVariable Long id )  {
        try {
            employeeService.deleteEmployee(id);
            String english = messageSource.getMessage("employee.deleted", new Object[]{id}, Locale.ENGLISH);
            String arabic = messageSource.getMessage("employee.deleted", new Object[]{id}, new Locale("ar"));
            BundleMessageDTO messageDTO = new BundleMessageDTO(english, arabic);
            return ResponseEntity.ok(messageDTO);
        } catch (RuntimeException | SystemException e) {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity <EmployeeDTO> getEmployeeById( @PathVariable long id) throws SystemException {
        return ResponseEntity.ok(employeeService.findEmployeeById(id));
    }

    @GetMapping("/search/by-ids")
    //GET /employees/search/by-ids?ids=1,2,3
    public ResponseEntity<List<EmployeeDTO>> getEmployeesByIds(@RequestParam List<Long> ids) throws SystemException {
        List<EmployeeDTO> employees = employeeService.getEmployeesByListOfId(ids);
        return ResponseEntity.ok(employees);
    }

    @GetMapping("/search/by-names")
    //GET /employees/search/by-names?names=  ,  ,
    public ResponseEntity<List<EmployeeDTO>> getEmployeesByNames(@RequestParam List<String> names) throws SystemException {
        List<EmployeeDTO> employees = employeeService.getEmployeesByListOfName(names);
        return ResponseEntity.ok(employees);
    }



}
